package com.laiteam.echowall.httpservice.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerificationCodeValidationResponse {
    private final String token;
}
