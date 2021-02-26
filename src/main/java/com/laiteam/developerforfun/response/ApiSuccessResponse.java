package com.laiteam.developerforfun.response;

import lombok.Getter;

@Getter
public class ApiSuccessResponse<T> extends ApiBaseResponse{
    private final T data;
    public ApiSuccessResponse(T data) {
        super(true);
        this.data = data;
    }
}
