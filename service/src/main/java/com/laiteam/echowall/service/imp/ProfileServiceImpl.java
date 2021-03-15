package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.dal.repository.ProfileRepository;
import com.laiteam.echowall.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Autowired
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

    @Override
    public Optional<Profile> findByUserId(Long id) {
        return Optional.ofNullable(profileRepository.findByUserId(id));
    }
}
