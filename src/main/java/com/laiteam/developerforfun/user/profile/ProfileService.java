package com.laiteam.developerforfun.user.profile;

import com.laiteam.developerforfun.user.User;

import java.util.Optional;

public interface ProfileService {
    Optional<Profile> getProfileById(Long id);

    Optional<Profile> saveProfile(Profile profile);
}
