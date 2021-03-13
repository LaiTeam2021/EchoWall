package com.laiteam.developerforfun.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    User findByEmail(String userName);
    User save(User user);
}
