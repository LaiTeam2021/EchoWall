package com.laiteam.developerforfun.user;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.laiteam.developerforfun.exception.InvalidRequestException;
import com.laiteam.developerforfun.jwt.JwtService;
import com.laiteam.developerforfun.response.UserWithToken;
import com.laiteam.developerforfun.user.profile.Profile;
import com.laiteam.developerforfun.user.profile.ProfileService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
public class RegisterApi {
    private final UserService userService;
    private final ProfileService profileService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Autowired
    public RegisterApi(UserService userService, ProfileService profileService, UserRepository userRepository, JwtService jwtService) {
        this.userService = userService;
        this.profileService = profileService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

//    @PostMapping("/users/register")
//    public ResponseEntity<?> saveUser(@Valid @RequestBody RegisterParam registerParam) {
//        Optional<User> user_in = userService.findByEmail(registerParam.getEmail());
//
//        if (userService.findByUserName(registerParam.getUsername()).isPresent()) {
//            throw new InvalidRequestException("Username exists");
//        } else if (user_in.isPresent()) {
//            throw new InvalidRequestException("email has been used");
//        }
//
//        Optional<User> user = userService.register(registerParam);
//
//        Optional<Profile> profile = profileService.register(registerParam, user.get());
//
//        return ResponseEntity.ok(user);
//    }
}
