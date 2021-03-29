package com.laiteam.echowall.service;

import com.laiteam.echowall.dal.entity.User;

import java.util.Optional;


public interface UserService {

    Optional<User> findUser(User user);

    Optional<User> saveUser(User user);

}
