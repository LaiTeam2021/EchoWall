package com.laiteam.developerforfun.exception;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class InvalidRequestException extends RuntimeException {
    private final String errorMessage;

    public InvalidRequestException(String errorMessage) {
        this.errorMessage = errorMessage;
    }


}
