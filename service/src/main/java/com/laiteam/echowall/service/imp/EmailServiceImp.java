package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.dal.entity.EmailInfo;
import com.laiteam.echowall.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(EmailInfo emailInfo) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(emailInfo.getFromAddress());
        mail.setTo(emailInfo.getToAddress());
        mail.setSubject(emailInfo.getSubject());
        mail.setText(emailInfo.getBody());
        mailSender.send(mail);
    }
}
