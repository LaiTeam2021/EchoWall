package com.laiteam.echowall.dal.entity;

import java.io.Serializable;
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
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    private String avatarUrl;
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.MALE;
    @Column(insertable = false)
    private String residence;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String aboutMe;

}
