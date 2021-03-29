package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.dal.entity.Topic;
import com.laiteam.echowall.dal.repository.FavoriteTopicsRepository;
import com.laiteam.echowall.dal.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.laiteam.echowall.service.TopicsService;

import java.util.List;

@Service
public class TopicsServiceImpl implements TopicsService {

    private final FavoriteTopicsRepository favoriteTopicsRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public TopicsServiceImpl(FavoriteTopicsRepository favoriteTopicsRepository,
      TopicRepository topicRepository) {
        this.favoriteTopicsRepository = favoriteTopicsRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> findTopicsByUserId(Long userId) {
        List<Topic> result = favoriteTopicsRepository.findTopicsByUserId(userId);
        if (result.isEmpty()) {
            return topicRepository.findTop2ByOrderByIdAsc();
        } else {
            return result;
        }
    }

}
