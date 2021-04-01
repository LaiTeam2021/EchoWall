package com.laiteam.echowall.httpservice.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.laiteam.echowall.common.exception.InvalidRequestException;
import com.laiteam.echowall.dal.entity.Gender;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.httpservice.response.ProfileResponse;
import com.laiteam.echowall.service.ProfileService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

@Controller
public class ProfileApi {

    private final ProfileService profileService;

    @Autowired
    public ProfileApi(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(path = "/profile/{id}")
    public ResponseEntity<?> userProfile(@PathVariable Long id) throws Throwable {
        return profileService.findByUserId(id)
          .map(it -> ResponseEntity.ok(ProfileResponse.convert(it)))
          .orElseThrow((Supplier<Throwable>) () -> new InvalidRequestException(
            "Can't find the current user's profile"));
    }


    @RequestMapping(path = "/profile/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveProfile(@RequestBody ProfileParam profileParam,
                                         @AuthenticationPrincipal User user) throws Throwable {
        Optional<Profile> optional = profileService.getProfileById(user.getId());
        boolean isEmpty = true;
        if (profileParam.getNickName() != null) {
            optional.get().setNickName(profileParam.getNickName());
            isEmpty = false;
        }

        if (profileParam.getAvatarUrl() != null) {
            optional.get().setAvatarUrl(profileParam.getAvatarUrl());
            isEmpty = false;
        }

        if (profileParam.getGender() != null) {
            optional.get().setGender(profileParam.getGender());
            isEmpty = false;
        }

        if (profileParam.getAboutMe() != null) {
            optional.get().setAboutMe(profileParam.getAboutMe());
            isEmpty = false;
        }

        if (profileParam.getDob() != null) {
            optional.get().setDob(profileParam.getDob());
            isEmpty = false;
        }

        if (profileParam.getResidence() != null) {
            optional.get().setResidence(profileParam.getResidence());
            isEmpty = false;
        }

        if (isEmpty) {
            throw new InvalidRequestException("No update is posted.");
        } else {
            return ResponseEntity.ok(profileService.saveProfile(optional.get()));
        }


    }

    @Getter
    @NoArgsConstructor
    static class ProfileParam {
        private Long userId;
        private String nickName;
        private String avatarUrl;
        private Gender gender;
        private String residence;
        private Date dob;
        private String aboutMe;
    }
}
