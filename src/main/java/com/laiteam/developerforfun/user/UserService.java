package com.laiteam.developerforfun.user;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    Optional<User> findByUserName(String username);

    Optional<User> findByEmail(String email);

    Optional<User> register(RegisterParam registerParam);
}
