package com.laiteam.developerforfun.user.profile;

import com.laiteam.developerforfun.exception.InvalidRequestException;
import com.laiteam.developerforfun.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.laiteam.developerforfun.user.gender.GenderType;

import java.util.Date;
import java.util.function.Supplier;

@Controller
public class ProfileApi {
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileApi(ProfileRepository repository) {
        this.profileRepository = repository;
    }

    @GetMapping(path = "/profile/{id}")
    public ResponseEntity<?> userProfile(@PathVariable Long id, @AuthenticationPrincipal User user) throws Throwable {
        return profileRepository.findById(id)
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
        return ResponseEntity.ok(profileRepository.save(profile));
    }

}
