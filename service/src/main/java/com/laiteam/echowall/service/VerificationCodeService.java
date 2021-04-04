package com.laiteam.echowall.service;

import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.dal.entity.UserVerificationCode;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Optional;

public interface VerificationCodeService {
    String generateDefaultVerificationCode();

    Optional<UserVerificationCode> findUserVerificationCode(UserVerificationCode userVerificationCode);

    Optional<UserVerificationCode> saveUserVerificationCode(User user, String code);

    ExampleMatcher getUserVerificationCodeMatcher();
}
