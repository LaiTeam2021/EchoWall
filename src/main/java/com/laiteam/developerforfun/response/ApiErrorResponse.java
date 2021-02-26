package com.laiteam.developerforfun.response;

import lombok.Getter;

@Getter
public class ApiErrorResponse extends ApiBaseResponse{
    private final String error;
    public ApiErrorResponse(String error) {
        super(false);
        this.error = error;
    }
}