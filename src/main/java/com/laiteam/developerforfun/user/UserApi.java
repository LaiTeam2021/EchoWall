package com.laiteam.developerforfun.user;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.laiteam.developerforfun.encrypt.EncryptService;
import com.laiteam.developerforfun.exception.InvalidRequestException;
import com.laiteam.developerforfun.jwt.JwtService;
import com.laiteam.developerforfun.response.UserWithToken;
import com.laiteam.developerforfun.util.ErrorUtil;
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

    @Autowired
    public UserApi(UserService userService, EncryptService encryptService, JwtService jwtService) {
        this.userService = userService;
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
}
