package com.laiteam.developerforfun.encrypt;

public interface EncryptService {
    String encrypt(String password);
    boolean check(String rawPassword, String encryptedPassword);
}
