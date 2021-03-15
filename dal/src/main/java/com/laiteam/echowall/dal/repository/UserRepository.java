package com.laiteam.echowall.dal.repository;

import com.laiteam.echowall.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    User findByEmail(String userName);
    User save(User user);
}