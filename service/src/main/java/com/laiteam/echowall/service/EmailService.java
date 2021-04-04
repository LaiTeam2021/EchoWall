package com.laiteam.echowall.service;

import com.laiteam.echowall.dal.entity.EmailInfo;

public interface EmailService {
    void sendEmail(EmailInfo emailInfo);
}
