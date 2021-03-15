package com.laiteam.echowall.dal.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@EqualsAndHashCode(of = {"favoriteTopicId"})
@Entity
@Setter
@Getter
@Table(name = "favorite_topics")
public class FavoriteTopics {
    @EmbeddedId
    private FavoriteTopicId favoriteTopicId;

    @MapsId("topicId")
    @ManyToOne
    private Topic topic;
}
