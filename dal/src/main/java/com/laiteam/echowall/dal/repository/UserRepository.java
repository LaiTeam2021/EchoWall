package com.laiteam.echowall.dal.repository;

import com.laiteam.echowall.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    Optional<User> findUserById(Long userId);
}
