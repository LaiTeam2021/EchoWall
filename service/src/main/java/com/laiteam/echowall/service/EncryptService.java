package com.laiteam.echowall.service;

public interface EncryptService {
    String encrypt(String password);
    boolean check(String rawPassword, String encryptedPassword);
}
