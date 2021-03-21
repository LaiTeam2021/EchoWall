package com.laiteam.echowall.service;

public interface VerificationCodeService {
    String randomDigits(int length);
    void sendVerificationCode(String code, String email);
    boolean checkVerificationCode(String code, String email);
}
