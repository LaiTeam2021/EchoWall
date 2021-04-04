package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.dal.entity.UserVerificationCode;
import com.laiteam.echowall.dal.repository.UserRepository;
import com.laiteam.echowall.dal.repository.UserVerificationCodeRepository;
import com.laiteam.echowall.service.VerificationCodeService;
import com.laiteam.echowall.service.util.OptionalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    final UserVerificationCodeRepository userVerificationCodeRepository;

    private static final int DEFAULT_VERIFICATION_CODE_LENGTH = 6;
    private static final int DEFAULT_VERIFICATION_CODE_EXPIRE_TIME = 600000;

    @Autowired
    public VerificationCodeServiceImpl(UserVerificationCodeRepository userVerificationCodeRepository){
        this.userVerificationCodeRepository = userVerificationCodeRepository;
    }

    @Override
    public String generateDefaultVerificationCode() {
        return generateVerificationCode(DEFAULT_VERIFICATION_CODE_LENGTH);
    }

    @Override
    public Optional<UserVerificationCode> findUserVerificationCode(UserVerificationCode userVerificationCode) {
        List<UserVerificationCode> userVerificationCodes = userVerificationCodeRepository.findAll(Example.of(userVerificationCode,getUserVerificationCodeMatcher()));
        return OptionalUtils.getFirstNullableItem(userVerificationCodes);
    }

    @Override
    public Optional<UserVerificationCode> saveUserVerificationCode(User user, String code) {
        Optional<UserVerificationCode> userVerificationCodeOptional = findUserVerificationCode(UserVerificationCode.builder().email(user.getEmail()).build());
        if(!userVerificationCodeOptional.isPresent()){
            userVerificationCodeOptional = Optional.of(UserVerificationCode.builder().email(user.getEmail()).build());
        }
        userVerificationCodeOptional.get().setVerificationCode(code);
        userVerificationCodeOptional.get().setExpirationTime(new Timestamp(System.currentTimeMillis() + DEFAULT_VERIFICATION_CODE_EXPIRE_TIME));
        return Optional.ofNullable(userVerificationCodeRepository.save(userVerificationCodeOptional.get()));
    }

    @Override
    public ExampleMatcher getUserVerificationCodeMatcher() {
        return ExampleMatcher.matchingAny().
                withIgnorePaths("verificationCode").
                withIgnorePaths("expirationTime").
                withIgnoreNullValues();
    }

    private String generateVerificationCode(int length) {
        Random generator = new Random();
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < length; i++){
            code.append(generator.nextInt(10));
        }
        return code.toString();
    }
}
