package com.laiteam.developerforfun.user.profile;

import com.laiteam.developerforfun.user.RegisterParam;
import com.laiteam.developerforfun.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laiteam.developerforfun.user.gender.GenderType;
import com.laiteam.developerforfun.user.gender.GenderService;

import java.util.Date;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final GenderService genderService;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, GenderService genderService) {
        this.profileRepository = profileRepository;
        this.genderService = genderService;
    }

    @Override
    public Optional<Profile> getProfileById(Long id) {
        return Optional.ofNullable(profileRepository.findByUserId(id));
    }

    @Override
    public Optional<Profile> saveProfile(Profile profile) {
        return Optional.of(profileRepository.save(profile));
    }

    @Override
    public Optional<Profile> register(RegisterParam registerParam, User user) {

        GenderType genderType = GenderType.builder()
                .id(Long.parseLong(registerParam.getGender()))
                .build();

        Profile profile = Profile.builder()
                .userId(user.getId())
                .gender(genderType)
                .residence(registerParam.getLocation())
                .avatarUrl("https://pa1.narvii.com/6404/35b2929ca438e295554d2460707145d35456f2c2_128.gif")
                .dob(new Date())
                .aboutMe("Lazy Man")
                .build();

        return Optional.ofNullable(profileRepository.save(profile));
    }
}
