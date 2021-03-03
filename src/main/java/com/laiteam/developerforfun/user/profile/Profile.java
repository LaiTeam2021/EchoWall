package com.laiteam.developerforfun.user.profile;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long userId;
    private String avatarUrl;
    @OneToOne
    @JoinColumn(name="gender", referencedColumnName = "id")
    private GenderType gender;
    private String residence;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String aboutMe;

    public String getGender() {
        return gender.getGender();
    }
}
