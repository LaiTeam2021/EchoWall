package com.laiteam.echowall.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Component
public class EmailInfo {
    private String fromAddress;
    private String toAddress;
    private String subject;
    private String body;

    private static final String VERIFICATION_CODE_HEADER =  "[No-repley]Your verification code from Echowall is here!";
    private static final String VERIFICATION_CODE_BODY = "Your verfication code is %s, please verify in 10 minutes";

    public static EmailInfo createVerificationCodeEmailInfo(String toAddress, String verificationCode){
        return EmailInfo.builder()
                .toAddress(toAddress)
                .subject(VERIFICATION_CODE_HEADER)
                .body(String.format(VERIFICATION_CODE_BODY, verificationCode))
                .build();
        }
}
