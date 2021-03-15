package com.laiteam.echowall.httpservice.api;

import com.laiteam.echowall.common.exception.InvalidRequestException;
import com.laiteam.echowall.dal.entity.GenderType;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.dal.entity.User;
import com.laiteam.echowall.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
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
    @GetMapping(path = "/profile/save")
    public ResponseEntity<?> saveProfile(@AuthenticationPrincipal User user) {
        GenderType genderType = new GenderType();
        genderType.setId(1L);
        Profile profile = Profile.builder().id(1L).userId(1L).avatarUrl("https://pa1.narvii.com/6404/35b2929ca438e295554d2460707145d35456f2c2_128.gif")
                .dob(new Date()).aboutMe("Lazy Man").gender(genderType).build();
        return ResponseEntity.ok(profileService.saveProfile(profile));
    }

}
