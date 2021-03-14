package com.laiteam.developerforfun.user;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.laiteam.developerforfun.encrypt.EncryptService;
import com.laiteam.developerforfun.exception.InvalidRequestException;
import com.laiteam.developerforfun.jwt.JwtService;
import com.laiteam.developerforfun.response.UserWithToken;
import com.laiteam.developerforfun.user.profile.Profile;
import com.laiteam.developerforfun.user.profile.ProfileService;
import com.laiteam.developerforfun.util.ErrorUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final ProfileService profileService;
    private final EncryptService encryptService;
    private final JwtService jwtService;

    @Autowired
    public UserApi(UserService userService, ProfileService profileService, EncryptService encryptService, JwtService jwtService) {
        this.userService = userService;
        this.profileService = profileService;
        this.encryptService = encryptService;
        this.jwtService = jwtService;
    }

    @RequestMapping(path = "/users/login", method = POST)
    public ResponseEntity<?> userLogin(@Valid @RequestBody LoginParam loginParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException(ErrorUtil.getErrorMessage(bindingResult));
        }
        Optional<User> optional = userService.findByEmail(loginParam.getEmail());
        if (optional.isPresent() && encryptService.check(loginParam.getPassword(), optional.get().getPassword())) {
            return ResponseEntity.ok(new UserWithToken(optional.get(), jwtService.toToken(optional.get())));
        } else {
            throw new InvalidRequestException("Invalid email or password");
        }
    }

    @PostMapping("/users/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody RegisterParam registerParam) {
        Optional<User> user_in = userService.findByEmail(registerParam.getEmail());

        if (userService.findByUserName(registerParam.getUsername()).isPresent()) {
            throw new InvalidRequestException("Username exists");
        } else if (user_in.isPresent()) {
            throw new InvalidRequestException("email has been used");
        }

        Optional<User> user = userService.register(registerParam);

        Optional<Profile> profile = profileService.register(registerParam, user.get());

        return ResponseEntity.ok(new UserWithToken(user.get(), jwtService.toToken(user.get())));
    }
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
}
