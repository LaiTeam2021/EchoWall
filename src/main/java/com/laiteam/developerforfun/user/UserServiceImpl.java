package com.laiteam.developerforfun.user;

import com.laiteam.developerforfun.encrypt.EncryptService;
import com.laiteam.developerforfun.user.profile.Profile;
import com.laiteam.developerforfun.user.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    final UserRepository userRepository;
    final ProfileService profileService;
    final EncryptService encryptService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProfileService profileService, EncryptService encryptService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
        this.encryptService = encryptService;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    @Override
    public Optional<User> register(RegisterParam registerParam) {

        User user = User.builder()
                .email(registerParam.getEmail())
                .password(encryptService.encrypt(registerParam.getPassword()))
                .username(registerParam.getUsername())
                .createDate(new Timestamp(new Date().getTime()))
                .isActive(true)
                .build();

        return Optional.ofNullable(userRepository.save(user));
    }
}
