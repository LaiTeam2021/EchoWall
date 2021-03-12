package com.laiteam.developerforfun.user.profile;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(of = {"id"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    private Long id;
    private Long userId;
    private String avatarUrl;
    @OneToOne
    @JoinColumn(name="gender_id", referencedColumnName = "id")
    private GenderType gender;
    private String residence;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String aboutMe;

    public String getGender() {
        return gender.getGender();
    }
}
