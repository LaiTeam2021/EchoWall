package com.laiteam.echowall.httpservice.response;

import com.laiteam.echowall.dal.entity.Topic;
import com.laiteam.echowall.httpservice.utils.BeanUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TopicResponse{
    private String name;
    private String nameCN;

    public static TopicResponse convert(Topic topic) {
        return BeanUtils.copyProperties(topic, TopicResponse.class);
    }

    public static List<TopicResponse> convertTopicLists(List<Topic> topics) {
        List<TopicResponse> topicResponses = new ArrayList<>();
        for (Topic topic : topics) {
            topicResponses.add(TopicResponse.convert(topic));
        }
        return topicResponses;
    }
}
