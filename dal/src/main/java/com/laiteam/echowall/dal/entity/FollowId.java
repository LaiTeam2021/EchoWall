package com.laiteam.echowall.dal.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"userId, followId"})
public class FollowId implements Serializable {
    private Long userId;
    private Long followId;
}
