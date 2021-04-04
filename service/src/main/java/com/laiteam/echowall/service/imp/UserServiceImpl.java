package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.dal.repository.UserRepository;
import com.laiteam.echowall.service.UserService;
import com.laiteam.echowall.service.util.OptionalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUser(User user) {
        List<User> users =
          userRepository.findAll(
            Example.of(user,
              ExampleMatcher.matchingAny().withIgnoreNullValues().withIgnorePaths("password")));
        return OptionalUtils.getFirstNullableItem(users);
    }

    @Override
    public Optional<User> saveUser(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

}
