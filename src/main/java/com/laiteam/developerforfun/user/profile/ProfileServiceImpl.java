package com.laiteam.developerforfun.user.profile;

import com.laiteam.developerforfun.user.RegisterParam;
import com.laiteam.developerforfun.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laiteam.developerforfun.user.gender.GenderType;
import com.laiteam.developerforfun.user.gender.GenderService;

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

        Profile profile = new Profile();
        profile.setUserId(user.getId());

        //create gender
        GenderType gender = new GenderType();
        gender.setId(1L);

        profile.setGender(gender);
        profile.setResidence(registerParam.getLocation());
        return Optional.ofNullable(profileRepository.save(profile));
//        return Optional.empty();
    }
}
