package com.laiteam.developerforfun.util;

import org.springframework.validation.BindingResult;

public class ErrorUtil {
    private ErrorUtil() {
    }

    public static String getErrorMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors().get(0).getDefaultMessage();
    }
}
