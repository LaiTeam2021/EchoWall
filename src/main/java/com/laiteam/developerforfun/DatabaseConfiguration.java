package com.laiteam.developerforfun;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DatabaseConfiguration {


    @Profile("dev")
    @Bean
    public String devDBConnection() {
        return "Connecting Dev DB";
    }

    @Profile("prod")
    @Bean
    public String prodDBConnection() {
        return "Connecting Prod DB";
    }
}
