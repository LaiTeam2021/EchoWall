package com.laiteam.echowall.httpservice.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserResponse {
    private final String token;
    private final List<TopicResponse> topics;
}
