package com.laiteam.echowall.dal.repository;

import com.laiteam.echowall.dal.entity.FavoriteTopicId;
import com.laiteam.echowall.dal.entity.FavoriteTopics;
import com.laiteam.echowall.dal.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteTopicsRepository extends JpaRepository<FavoriteTopics, FavoriteTopicId> {

    @Query("select t.topic from FavoriteTopics t where t.favoriteTopicId.userId = (:userId)")
    List<Topic> findTopicsByUserId(Long userId);
}
