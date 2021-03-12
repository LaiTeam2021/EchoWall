package com.laiteam.developerforfun.topics;

import com.laiteam.developerforfun.topics.entity.Topic;
import com.laiteam.developerforfun.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TopicsApi {
    private final TopicsService topicsService;

    public TopicsApi(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @RequestMapping(path = "/topics/get/", method = GET)
    public ResponseEntity<List<Topic>> userLogin(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(topicsService.findTopicsByUserId(user.getId()));
    }
}


