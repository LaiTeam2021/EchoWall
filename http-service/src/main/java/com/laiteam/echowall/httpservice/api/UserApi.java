package com.laiteam.echowall.httpservice.api;

import com.laiteam.echowall.common.exception.InvalidRequestException;
import com.laiteam.echowall.common.util.ErrorUtil;
import com.laiteam.echowall.dal.entity.Gender;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.httpservice.response.TopicResponse;
import com.laiteam.echowall.httpservice.response.UserResponse;
import com.laiteam.echowall.dal.entity.*;
import com.laiteam.echowall.httpservice.response.*;
import com.laiteam.echowall.service.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserApi {

    private final UserService userService;
    private final ProfileService profileService;
    private final EncryptService encryptService;
    private final JwtService jwtService;
    private final TopicsService topicsService;
    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;

    private static final int DEFAULT_MINIMUM_PASSWORD_LENGTH = 6;

    @Autowired
    public UserApi(UserService userService,
                   ProfileService profileService,
                   EncryptService encryptService,
                   JwtService jwtService,
                   TopicsService topicsService,
                   EmailService emailService,
                   VerificationCodeService verificationCodeService) {

        this.userService = userService;
        this.profileService = profileService;
        this.encryptService = encryptService;
        this.jwtService = jwtService;
        this.topicsService = topicsService;
        this.emailService = emailService;
        this.verificationCodeService = verificationCodeService;

    }

    @RequestMapping(path = "/users/login", method = POST)
    public ResponseEntity<?> userLogin(
      @Valid @RequestBody LoginParam loginParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }
        Optional<User> optional =
          userService.findUser(
            User.builder()
              .email(loginParam.getEmail())
              .build());
        if (optional.isPresent() && encryptService
          .check(loginParam.getPassword(), optional.get().getPassword())) {
            UserResponse userResponse = createUserResponse(optional.get(),
              loginParam.isHasTopics());
            return ResponseEntity.ok(userResponse);
        } else {
            throw new InvalidRequestException("Invalid email or password");
        }
    }

    @RequestMapping(path = "/users/register", method = POST)
    public ResponseEntity<?> userRegister(
      @Valid @RequestBody RegisterParam registerParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }

        User user = User.builder()
          .email(registerParam.getEmail())
          .username(registerParam.getUsername())
          .password(encryptService.encrypt(registerParam.getPassword()))
          .build();
        if (userService.findUser(user).isPresent()) {
            throw new InvalidRequestException("Either username or email has been registered");
        }

        Optional<User> optional = userService.saveUser(user);
        if (!optional.isPresent()) {
            throw new InvalidRequestException("Internal system error");
        } else {
            profileService.saveProfile(
              Profile.builder().user(user).gender(Gender.FEMALE).build());
        }
        UserResponse userResponse = createUserResponse(optional.get(), registerParam.isHasTopics());
        return ResponseEntity.ok(userResponse);
    }

    @RequestMapping(path = "/users/verification_code", method = POST)
    public ResponseEntity<?> userRequestVerificationCode(@Valid @RequestBody VerificationCodeParam verificationCodeParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }
        User user = User.builder().email(verificationCodeParam.getEmail()).build();
        if (!userService.findUser(user).isPresent()) {
            throw new InvalidRequestException("Email not found");
        }
        String code = verificationCodeService.generateDefaultVerificationCode();
        verificationCodeService.saveUserVerificationCode(user, code);
        emailService.sendEmail(EmailInfo.createVerificationCodeEmailInfo(verificationCodeParam.getEmail(), code));
        return ResponseEntity.ok(SystemResponse.builder().message("verification code is sent").build());
    }

    @RequestMapping(path = "/users/verification_code_validation", method = POST)
    public ResponseEntity<?> userValidateVerificationCode(@Valid @RequestBody VerificationCodeValidationParam verificationCodeValidationParam,
                                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }
        UserVerificationCode userVerificationCode = UserVerificationCode.builder()
                .email(verificationCodeValidationParam.getEmail())
                .build();
        Optional<UserVerificationCode> userVerificationCodeOptional = verificationCodeService.findUserVerificationCode(userVerificationCode);
        if(!userVerificationCodeOptional.isPresent()){
            throw new InvalidRequestException("Invalid verification code");
        }
        Optional<User> curUser = userService.findUser(User.builder().email(verificationCodeValidationParam.getEmail()).build());
        if (!curUser.isPresent() || !validateVerificationCode(userVerificationCodeOptional, verificationCodeValidationParam.getVerificationCode())) {
            throw new InvalidRequestException("Invalid verification code");
        }
        return ResponseEntity.ok(VerificationCodeValidationResponse
                .builder()
                .token(jwtService.to15MinToken(curUser.get()))
                .build());
    }
    //TODO: [API] resetPassword: need authorized user(token), and resetPassword
    @RequestMapping(path = "users/password_reset", method = POST)
    public ResponseEntity<?> userResetPassword(@AuthenticationPrincipal User user,
                                               @Valid @RequestBody ResetPasswordParam resetPasswordParam,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }
        Optional<User> curUser = userService.findUser(User.builder().email(resetPasswordParam.getEmail()).build());
        if(!curUser.isPresent()){
            throw new InvalidRequestException("Email does not exist");
        }
        if(!isValidPassword(resetPasswordParam.getNewPassword())){
            throw new InvalidRequestException("Invalid Password");
        }
        curUser.get().setPassword(encryptService.encrypt(resetPasswordParam.getNewPassword()));
        userService.saveUser(curUser.get());
        return ResponseEntity.ok(SystemResponse.builder().message("Succeess").build());
    }

    private UserResponse createUserResponse(User user, boolean hasTopics) {
        UserResponse.UserResponseBuilder responseBuilder =
          UserResponse.builder().token(jwtService.toToken(user));
        if (hasTopics) {
            responseBuilder.topics(
              TopicResponse.convertTopicLists(topicsService.findTopicsByUserId(user.getId())));
        }
        return responseBuilder.build();
    }

    private boolean validateVerificationCode(Optional<UserVerificationCode> optional, String verificationCode){
        if (!optional.isPresent()
                || !optional.get().getVerificationCode().equals(verificationCode)
                || optional.get().getExpirationTime().before(new Timestamp(System.currentTimeMillis()))) {
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password){
        return password.length() >= DEFAULT_MINIMUM_PASSWORD_LENGTH;
    }
}

@Getter
@NoArgsConstructor
class RegisterParam extends LoginParam {

    @NotBlank(message = "Username can't be empty")
    private String username;
}

@Getter
@NoArgsConstructor
class LoginParam {

    @NotBlank(message = "Email can't be empty")
    @Email(message = "should be an email")
    private String email;

    @NotBlank(message = "Password can't be empty")
    private String password;

    private boolean hasTopics;
}

@Getter
@NoArgsConstructor
class VerificationCodeParam {
    @NotBlank(message = "Email can't be empty")
    @Email(message = "should be an email")
    private String email;
}

@Getter
@NoArgsConstructor
class VerificationCodeValidationParam {
    @NotBlank(message = "Email can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank
    private String verificationCode;
}

@Getter
@NoArgsConstructor
class ResetPasswordParam {
    @NotBlank(message = "Email can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank
    private String newPassword;
}
