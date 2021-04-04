package com.laiteam.echowall.dal.repository;

import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.dal.entity.UserVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserVerificationCodeRepository extends JpaRepository<UserVerificationCode, Long> {
    UserVerificationCode save(UserVerificationCode userVerificationCode);
}
