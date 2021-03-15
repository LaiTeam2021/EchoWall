package com.laiteam.echowall.service;

import com.laiteam.echowall.dal.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    Optional<User> findByUserName(String username);

    Optional<User> findByEmail(String email);

    Optional<User> saveUser(User user);

}
