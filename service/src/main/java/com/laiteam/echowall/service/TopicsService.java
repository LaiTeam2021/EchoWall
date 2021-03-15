package com.laiteam.echowall.service;


import com.laiteam.echowall.dal.entity.Topic;

import java.util.List;

public interface TopicsService {
    List<Topic> findTopicsByUserId(Long userId);
}
