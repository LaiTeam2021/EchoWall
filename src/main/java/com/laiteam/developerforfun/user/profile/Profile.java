package com.laiteam.developerforfun.user.profile;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import com.laiteam.developerforfun.user.GenderType;


@EqualsAndHashCode(of = {"id"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "profile")
@SequenceGenerator(
        name="profile_seq",
        sequenceName="profile_sequence", allocationSize = 1, initialValue = 10001)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_seq")
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

//    public String getGender() {
//        return gender.getGender();
//    }
}
