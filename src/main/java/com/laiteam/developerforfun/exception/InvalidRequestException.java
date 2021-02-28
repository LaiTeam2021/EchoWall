package com.laiteam.developerforfun.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String errorMessage) {
        super(errorMessage);
    }


}
