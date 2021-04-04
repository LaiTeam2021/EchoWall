package com.laiteam.echowall.service;

import com.laiteam.echowall.dal.entity.Follow;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.service.dto.Pagination;

import java.util.Optional;

public interface FollowService {
    Optional<Follow> followUser(Long userId, Long followId);

    Pagination<Profile> getAllFollowers(Long userId, Integer page, Integer size);

    Optional<Profile> deleteFollower(Long userId, Long followId);

    Optional<Follow> isFollowed(Long userId, Long followId);
}
