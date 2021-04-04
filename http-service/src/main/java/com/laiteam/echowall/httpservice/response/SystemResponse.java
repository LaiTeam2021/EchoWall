package com.laiteam.echowall.httpservice.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class SystemResponse {
    private String message;
}