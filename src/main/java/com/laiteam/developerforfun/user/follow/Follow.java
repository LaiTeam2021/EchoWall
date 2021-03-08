package com.laiteam.developerforfun.user.follow;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "follow")
public class Follow {


    private long user_id;
    private long follow_id;

}
