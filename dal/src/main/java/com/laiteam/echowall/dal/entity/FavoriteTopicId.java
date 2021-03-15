package com.laiteam.echowall.dal.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode(of = {"userId, topicId"})
public class FavoriteTopicId implements Serializable {
    private Long userId;
    private Long topicId;
}
