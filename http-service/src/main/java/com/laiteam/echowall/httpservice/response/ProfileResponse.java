package com.laiteam.echowall.httpservice.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.httpservice.utils.BeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ProfileResponse {

    private Long userId;
    private String username;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String residence;
    private Date dob;
    private String aboutMe;

    public static ProfileResponse convert(Profile profile) {
        ProfileResponse response = BeanUtils.copyProperties(profile, ProfileResponse.class);
        response.setUserId(profile.getUser().getId());
        response.setGender(profile.getGender().name());
        response.setEmail(profile.getUser().getEmail());
        response.setUsername(profile.getUser().getUsername());
        response.setCreateDate(profile.getUser().getCreateDate());
        return response;
    }

    public static List<ProfileResponse> convertList(List<Profile> profileList) {
        List<ProfileResponse> response = new ArrayList<>();
        for (Profile profile : profileList) {
            response.add(ProfileResponse.convert(profile));
        }
        return response;
    }
}
