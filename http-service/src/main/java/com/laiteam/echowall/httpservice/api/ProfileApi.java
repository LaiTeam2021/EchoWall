package com.laiteam.echowall.httpservice.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.laiteam.echowall.common.exception.InvalidRequestException;
import com.laiteam.echowall.dal.entity.GenderType;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.dal.entity.User;
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
    public ResponseEntity<?> userProfile(@PathVariable Long id, @AuthenticationPrincipal User user) throws Throwable {
        return profileService.findByUserId(id)
                .map(it -> ResponseEntity.ok(it))
                .orElseThrow((Supplier<Throwable>) () -> new InvalidRequestException("Can't find user profile"));
    }

    //TODO("Just an example")
//    @GetMapping(path = "/profile/save")
//    public ResponseEntity<?> saveProfileExample(@AuthenticationPrincipal User user) {
//        GenderType genderType = new GenderType();
//        genderType.setId(1L);
//        Profile profile = Profile.builder().id(1L).userId(1L).avatarUrl("https://pa1.narvii.com/6404/35b2929ca438e295554d2460707145d35456f2c2_128.gif")
//                .dob(new Date()).aboutMe("Lazy Man").gender(genderType).build();
//        return ResponseEntity.ok(profileService.saveProfile(profile));
//
//    }

    @RequestMapping(path = "/profile/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveProfile(@RequestBody(required = false) ProfileParam profileParam, @AuthenticationPrincipal User user) {
        Optional<Profile> optional = profileService.getProfileById(user.getId());
        if (profileParam.getUserId() != null) {
            optional.get().setUserId(profileParam.getUserId());
        }

        if (profileParam.getAvatarUrl() != null) {
            optional.get().setAvatarUrl(profileParam.getAvatarUrl());
        }

        if (profileParam.getGender() != null) {
            optional.get().setGender(profileParam.getGender());
        }

        if (profileParam.getAboutMe() != null) {
            optional.get().setAboutMe(profileParam.getAboutMe());
        }

        if (profileParam.getDob() != null) {
            optional.get().setDob(profileParam.getDob());
        }

        if (profileParam.getResidence() != null) {
            optional.get().setResidence(profileParam.getResidence());
        }

        return ResponseEntity.ok(profileService.saveProfile(optional.get()));

    }



}

@Getter
@JsonRootName("user")
@NoArgsConstructor
class ProfileParam {
    private Long userId;
    private String avatarUrl;
    private GenderType gender;
    private String residence;
    private Date dob;
    private String aboutMe;
}
