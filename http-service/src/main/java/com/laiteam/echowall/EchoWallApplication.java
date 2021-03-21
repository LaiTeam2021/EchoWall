package com.laiteam.echowall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.laiteam.echowall.service", "com.laiteam.echowall.dal.repository", "com.laiteam.echowall.httpservice", "com.laiteam.echowall.common", "com.laiteam.echowall.sal"})
public class EchoWallApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoWallApplication.class, args);
    }

}
