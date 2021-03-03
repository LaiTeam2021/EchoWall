package com.laiteam.developerforfun.user.profile;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> getProfileById(Long id) {
        return Optional.ofNullable(profileRepository.findByUserId(id));
    }

    @Override
    public Optional<Profile> saveProfile(Profile profile) {
        return Optional.of(profileRepository.save(profile));
    }
}
