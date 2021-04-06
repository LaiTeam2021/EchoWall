package com.laiteam.echowall.httpservice.api;

import com.laiteam.echowall.common.exception.InvalidRequestException;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.httpservice.response.PaginationResponse;
import com.laiteam.echowall.httpservice.response.ProfileResponse;
import com.laiteam.echowall.service.FollowService;
import com.laiteam.echowall.service.ProfileService;
import com.laiteam.echowall.service.UserService;
import com.laiteam.echowall.service.dto.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@RestController
@AllArgsConstructor
public class FollowApi {
    private final FollowService followService;
    private final ProfileService profileService;
    private final UserService userService;

    @PostMapping(path = "/follow/all/{userId}")
    public ResponseEntity<PaginationResponse<ProfileResponse>> getAllFollowers(@PathVariable Long userId,
                                         @RequestParam(defaultValue = "1", name = "page") Integer page,
                                         @RequestParam(defaultValue = "10", name = "size") Integer size) {
        if (!userService.findUserId(userId).isPresent()) {
            throw new InvalidRequestException("Can't find user");
        }
        Pagination<Profile> followerList = followService.getAllFollowers(userId, page, size);
        return ResponseEntity.ok(PaginationResponse.convertProfileResponse(followerList));
    }

    @PostMapping(path = "/follow/{followerId}")
    public ResponseEntity<ProfileResponse> follow(@AuthenticationPrincipal User user, @PathVariable Long followerId) throws Throwable {
        Long userId = user.getId();
        if (!userService.findUserId(followerId).isPresent()) {
            throw new InvalidRequestException("Can't find this follower");
        }
        if (followService.isFollowed(userId, followerId).isPresent()) {
            throw new InvalidRequestException("Already followed this user");
        }
        followService.followUser(userId, followerId);
        return profileService.findByUserId(followerId)
                .map(it -> ResponseEntity.ok(ProfileResponse.convert(it)))
                .orElseThrow((Supplier<Throwable>) () -> new InvalidRequestException(
                        "Can't find this follower"));
    }

    @PostMapping(path = "/unfollow/{followerId}")
    public ResponseEntity<ProfileResponse> unFollow(@AuthenticationPrincipal User user, @PathVariable Long followerId) throws Throwable {
        Long userId = user.getId();
        if (!followService.isFollowed(userId, followerId).isPresent()) {
            throw new InvalidRequestException("You are not following this user");
        }
        followService.deleteFollower(userId, followerId);
        return profileService.findByUserId(followerId)
                .map(it -> ResponseEntity.ok(ProfileResponse.convert(it)))
                .orElseThrow((Supplier<Throwable>) () -> new InvalidRequestException(
                        "Can't find this follower"));
    }
}
