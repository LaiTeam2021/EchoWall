package com.laiteam.echowall.service;

import com.laiteam.echowall.dal.entity.Profile;

import java.util.Optional;

public interface ProfileService {
    Optional<Profile> getProfileById(Long id);

    Optional<Profile> saveProfile(Profile profile);

    Optional<Profile> findByUserId(Long id);
}
