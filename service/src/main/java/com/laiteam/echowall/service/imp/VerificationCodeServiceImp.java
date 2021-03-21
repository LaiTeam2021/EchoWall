package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.service.VerificationCodeService;
import com.laiteam.echowall.sal.firestoreDB.FireStoreDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VerificationCodeServiceImp implements VerificationCodeService {
    FireStoreDB fireStoreDB;

    @Autowired
    public VerificationCodeServiceImp(FireStoreDB fireStoreDB){
        this.fireStoreDB = fireStoreDB;
    }

    @Override
    public String randomDigits(int length) {
        StringBuilder code = new StringBuilder();
        for(int i = 0; i < length; i++){
            code.append(getRandomNumber());
        }
        return code.toString();
    }

    @Override
    public void sendVerificationCode(String code, String email) {
        fireStoreDB.triggerVerificationCodeEmail(email, code);
    }

    @Override
    public boolean checkVerificationCode(String code, String email){
        return fireStoreDB.checkVerificationCode(email, code);
    }

    private int getRandomNumber(){
        Random generator = new Random();
        return generator.nextInt(10);
    }
}
