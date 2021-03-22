package com.laiteam.echowall.httpservice.api;

import com.laiteam.echowall.common.exception.InvalidRequestException;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.httpservice.response.FollowResponse;
import com.laiteam.echowall.httpservice.response.ProfileResponse;
import com.laiteam.echowall.service.FollowService;
import com.laiteam.echowall.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@AllArgsConstructor
public class FollowApi {
    private final FollowService followService;
    private final ProfileService profileService;

    @GetMapping(path = "/follow/{userId}")
    public ResponseEntity<?> getAllFollowers(@PathVariable Long userId,
                                         @RequestParam(defaultValue = "1", name = "page") Integer page,
                                         @RequestParam(defaultValue = "10", name = "size") Integer size) {
        if (!profileService.findByUserId(userId).isPresent()) {
            throw new InvalidRequestException("Can't find user");
        }
        List<Profile> followerList = followService.getAllFollowers(userId, page, size);
        return ResponseEntity.ok(FollowResponse.convertFollowers(userId, followerList));
    }

    @PostMapping(path = "/follow/{followId}")
    public ResponseEntity<?> follow(@AuthenticationPrincipal User user, @PathVariable Long followId) throws Throwable {
        Long userId = user.getId();
        if (!profileService.getProfileById(followId).isPresent()) {
            throw new InvalidRequestException("Can't find this follower");
        }
        if (followService.getFollower(userId, followId).isPresent()) {
            throw new InvalidRequestException("Already followed this user");
        }
        followService.followUser(userId, followId);
        return profileService.findByUserId(followId)
                .map(it -> ResponseEntity.ok(FollowResponse.convert(userId, followId, ProfileResponse.convert(it))))
                .orElseThrow((Supplier<Throwable>) () -> new InvalidRequestException(
                        "Can't find this follower"));
    }

    @PostMapping(path = "/unfollow/{followId}")
    public ResponseEntity<?> unFollow(@AuthenticationPrincipal User user, @PathVariable Long followId) throws Throwable {
        Long userId = user.getId();
        if (!followService.getFollower(userId, followId).isPresent()) {
            throw new InvalidRequestException("You are not following this user");
        }
        followService.deleteFollower(userId, followId);
        return profileService.findByUserId(followId)
                .map(it -> ResponseEntity.ok(ProfileResponse.convert(it)))
                .orElseThrow((Supplier<Throwable>) () -> new InvalidRequestException(
                        "Can't find this follower"));
    }
}
