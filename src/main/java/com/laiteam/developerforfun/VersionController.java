package com.laiteam.developerforfun;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/v1/version")
    @ResponseBody
    public String version() {
        return "0.0.1";
    }


    @GetMapping("/")
    @ResponseBody
    public String healthCheck() {
        return "OK";
    }

}