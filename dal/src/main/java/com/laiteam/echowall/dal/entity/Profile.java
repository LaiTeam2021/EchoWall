package com.laiteam.echowall.dal.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickName;
    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;
    private String avatarUrl;
    @OneToOne
    @JoinColumn(name="gender_id", referencedColumnName = "id")
    private GenderType gender;
    @Column(insertable = false)
    private String residence;
    @Temporal(TemporalType.DATE)
    private Date dob;
    private String aboutMe;

}
