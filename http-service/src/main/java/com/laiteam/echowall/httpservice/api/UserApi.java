package com.laiteam.echowall.httpservice.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.laiteam.echowall.common.exception.InvalidRequestException;
import com.laiteam.echowall.common.util.ErrorUtil;
import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.httpservice.response.UserWithToken;
import com.laiteam.echowall.service.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserApi {
    private final UserService userService;
    private final EncryptService encryptService;
    private final JwtService jwtService;
    private final TopicsService topicsService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    public UserApi(UserService userService, EncryptService encryptService, JwtService jwtService, TopicsService topicsService,
                   VerificationCodeService verificationCodeService) {
        this.userService = userService;
        this.encryptService = encryptService;
        this.jwtService = jwtService;
        this.topicsService = topicsService;
        this.verificationCodeService = verificationCodeService;
    }

    @RequestMapping(path = "/users/login", method = POST)
    public ResponseEntity<?> userLogin(@Valid @RequestBody LoginParam loginParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }
        Optional<User> optional = userService.findByEmail(loginParam.getEmail());
        if (optional.isPresent() && encryptService.check(loginParam.getPassword(), optional.get().getPassword())) {
            UserWithToken userWithToken = new UserWithToken(optional.get(), jwtService.toToken(optional.get()));
            if (loginParam.isHasTopics()) {
                userWithToken.setTopics(topicsService.findTopicsByUserId(optional.get().getId()));
            }
            return ResponseEntity.ok(userWithToken);
        } else {
            throw new InvalidRequestException("Invalid email or password");
        }
    }

    @RequestMapping(path = "/users/register", method = POST)
    public ResponseEntity<?> userRegister(@Valid @RequestBody RegisterParam registerParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }

        //TODO check deduplicate
        User user = User.builder().email(registerParam.getEmail()).password(registerParam.getPassword()).username(registerParam.getUsername()).
                isActive(true).build();
        Optional<User> optional = userService.saveUser(user);
        return ResponseEntity.ok(new UserWithToken(optional.get(), jwtService.toToken(optional.get())));
    }

    @RequestMapping(path = "/users/password", method = POST)
    public ResponseEntity<?> userResetPassword(@Valid @RequestBody ResetPasswordParam resetPasswordParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }
        // TODO: still need to check verification code, and check new password is legal
        Optional<User> optional = userService.findByEmail(resetPasswordParam.getEmail());
        if (!optional.isPresent()) {
            throw new InvalidRequestException("No such user, please register first!");
        } else if(!verificationCodeService.checkVerificationCode(resetPasswordParam.getVerificationCode(), resetPasswordParam.getEmail())){
            throw new InvalidRequestException("Invalid verification code or verification code out of date!");
        } else if(resetPasswordParam.getPassword().length() < 6){
            throw new InvalidRequestException("Password must be at least 6 characters!");
        } else {
            User curUser = optional.get();
            curUser.setPassword(encryptService.encrypt(resetPasswordParam.getPassword()));
            optional = userService.saveUser(curUser);
            return ResponseEntity.ok("Reset password successfully!");
        }
    }

    @RequestMapping(path = "/users/verification_code", method = POST)
    public ResponseEntity<?> userRequestVerificationCode(@Valid @RequestBody RequestVerificationCodeParam requestVerificationCodeParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }
        // TODO: still need to check verification code, and check new password is legal
        Optional<User> optional = userService.findByEmail(requestVerificationCodeParam.getEmail());
        if (!optional.isPresent()) {
            throw new InvalidRequestException("No such user, please register first!");
        } else {
            verificationCodeService.sendVerificationCode(verificationCodeService.randomDigits(6), requestVerificationCodeParam.getEmail());
            return ResponseEntity.ok("Verification Code is sent!");
        }
    }

    @RequestMapping(path = "/users/verification_code_check", method = POST)
    public ResponseEntity<?> userVerificationCodeCheck(@Valid @RequestBody CheckVerificationCodeParam checkVerificationCodeParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }
        // TODO: still need to check verification code, and check new password is legal
        if (!verificationCodeService.checkVerificationCode(checkVerificationCodeParam.getCode(), checkVerificationCodeParam.getEmail())) {
            throw new InvalidRequestException("Invalid Verification code!");
        } else {
            return ResponseEntity.ok("");
        }
    }


}

@Getter
@JsonRootName("user")
@NoArgsConstructor
class RegisterParam {
    @NotBlank(message = "Email can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank(message = "Username can't be empty")
    private String username;
    @NotBlank(message = "Password can't be empty")
    private String password;
}

@Getter
@JsonRootName("user")
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
@JsonRootName("user")
@NoArgsConstructor
class ResetPasswordParam {
    @NotBlank(message = "Email can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @NotBlank(message = "Verification code can't be empty")
    private String verificationCode;
}

@Getter
@JsonRootName("user")
@NoArgsConstructor
class RequestVerificationCodeParam {
    @NotBlank(message = "Email can't be empty")
    @Email(message = "should be an email")
    private String email;
}

@Getter
@JsonRootName("user")
@NoArgsConstructor
class CheckVerificationCodeParam {
    @NotBlank(message = "Email can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank(message = "Verification code can't be empty")
    private String code;
}
