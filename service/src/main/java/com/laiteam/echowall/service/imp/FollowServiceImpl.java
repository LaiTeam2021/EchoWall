package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.dal.entity.Follow;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.dal.repository.FollowRepository;
import com.laiteam.echowall.dal.repository.ProfileRepository;
import com.laiteam.echowall.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;

    private final ProfileRepository profileRepository;

    @Autowired
    public FollowServiceImpl(FollowRepository followRepository, ProfileRepository profileRepository) {
        this.followRepository = followRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Follow> followUser(Long userId, Long followId) {
        Follow follow = Follow.builder().userId(userId).followId(followId).build();
        return Optional.of(followRepository.save(follow));
    }

    @Override
    public List<Profile> getAllFollowers(Long userId, Integer page, Integer size) {
        page = page <= 1 ? 0 : page - 1;
        return followRepository.findAllByUserId(userId, PageRequest.of(page, size)).getContent();
    }

    @Override
    @Transactional
    public Optional<Profile> deleteFollower(Long userId, Long followId) {
        followRepository.deleteFollowByFollowId(userId, followId);
        return profileRepository.findById(followId);
    }

    @Override
    public Optional<Follow> getFollower(Long userId, Long followId) {
        return followRepository.findFollowByUserIdAndFollowId(userId, followId);
    }
}
