package com.laiteam.developerforfun.user;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@JsonRootName("user")
@NoArgsConstructor
public class RegisterParam {
    @NotBlank(message = "username can't be empty")
    private String username;
    @NotBlank(message = "Email can't be empty")
    @Email(message = "should be an email")
    private String email;
    @NotBlank(message = "Password can't be empty")
    private String password;
    @NotBlank(message = "Confirming Password can't be empty")
    private String confirmPassword;
    @NotBlank(message = "Gender can't be empty")
    private String gender;
    @NotBlank(message = "Location can't be empty")
    private String location;
}