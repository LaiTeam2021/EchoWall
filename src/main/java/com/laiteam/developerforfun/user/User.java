package com.laiteam.developerforfun.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "users")
@SequenceGenerator(
        name="user_seq",
        sequenceName="user_sequence", allocationSize = 1, initialValue = 10001)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    private String username;
    private String email;
    private String password;
    private Date createDate;
    private boolean isActive;
}
