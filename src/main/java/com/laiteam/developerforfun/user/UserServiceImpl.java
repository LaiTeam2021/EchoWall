package com.laiteam.developerforfun.user;

import com.laiteam.developerforfun.user.profile.Profile;
import com.laiteam.developerforfun.user.profile.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    final UserRepository userRepository;
    final ProfileService profileService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        User user = new User();
        user.setUsername(registerParam.getUsername());
        user.setEmail(registerParam.getEmail());
        user.setCreateDate(new Date());
        user.setPassword(registerParam.getPassword());

//        Profile profile = new Profile();
//        profile.setUserId(user.getUsername());
//        profile.setGender(1);
//        profileService.saveProfile(profile);

        return Optional.ofNullable(userRepository.save(user));
    }
}
