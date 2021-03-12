package com.laiteam.developerforfun.topics;

import com.laiteam.developerforfun.topics.entity.Topic;

import java.util.List;

public interface TopicsService {
    List<Topic> findTopicsByUserId(Long userId);
}
