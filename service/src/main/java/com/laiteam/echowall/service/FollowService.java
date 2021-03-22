package com.laiteam.echowall.service;

import com.laiteam.echowall.dal.entity.Follow;
import com.laiteam.echowall.dal.entity.Profile;

import java.util.List;
import java.util.Optional;

public interface FollowService {
    Optional<Follow> followUser(Long userId, Long followId);

    List<Profile> getAllFollowers(Long userId, Integer page, Integer size);

    Optional<Profile> deleteFollower(Long userId, Long followId);

    Optional<Follow> getFollower(Long userId, Long followId);
}
