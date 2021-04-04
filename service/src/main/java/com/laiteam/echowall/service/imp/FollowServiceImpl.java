package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.dal.entity.Follow;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.dal.repository.FollowRepository;
import com.laiteam.echowall.dal.repository.ProfileRepository;
import com.laiteam.echowall.service.FollowService;
import com.laiteam.echowall.service.dto.Pagination;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final ProfileRepository profileRepository;

    @Override
    public Optional<Follow> followUser(Long userId, Long followId) {
        Follow follow = Follow.builder().userId(userId).followId(followId).build();
        return Optional.of(followRepository.save(follow));
    }

    @Override
    public Pagination<Profile> getAllFollowers(Long userId, Integer page, Integer size) {
        page = page <= 1 ? 0 : page - 1;
        Page<Profile> response = followRepository.findAllByUserId(userId, PageRequest.of(page, size));
        Pagination pagination = new Pagination(response.getContent(), response.getTotalElements(), response.getTotalPages());
        pagination.setCurrentPage(page + 1);
        pagination.setPageSize(size);
        return pagination;
    }

    @Override
    @Transactional
    public Optional<Profile> deleteFollower(Long userId, Long followId) {
        followRepository.deleteFollowByFollowId(userId, followId);
        return profileRepository.findById(followId);
    }

    @Override
    public Optional<Follow> isFollowed(Long userId, Long followId) {
        return followRepository.findFollowByUserIdAndFollowId(userId, followId);
    }
}
