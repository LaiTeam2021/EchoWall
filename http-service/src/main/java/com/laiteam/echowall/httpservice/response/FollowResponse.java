package com.laiteam.echowall.httpservice.response;

import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.httpservice.utils.BeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class FollowResponse {

    private Long userId;
    private Long followerId;
    private ProfileResponse follower;

    public static FollowResponse convert(Long userId, Long followerId,ProfileResponse follower) {
        FollowResponse response = new FollowResponse();
        ProfileResponse profileResponse = BeanUtils.copyProperties(follower, ProfileResponse.class);
        response.setUserId(userId);
        response.setFollowerId(followerId);
        response.setFollower(profileResponse);
        return response;
    }

    public static List<FollowResponse> convertFollowers(Long userId, List<Profile> profileList) {
        List<FollowResponse> followerResponse = new ArrayList<>();
        for (Profile profile : profileList) {
            followerResponse.add(FollowResponse.convert(userId, profile.getUser().getId(),ProfileResponse.convert(profile)));
        }
        return followerResponse;
    }
}
