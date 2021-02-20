package com.laiteam.developerforfun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;

@RestController
public class StatusController {

    @Value("${app.name}")
    private String name;
    @Value("${app.environment}")
    private String environment;
    @Value("${app.version}")
    private String version;

    @GetMapping("/")
    @ResponseBody
    public HashMap<String, String> healthCheck() throws IllegalAccessException {
        HashMap<String, String> map = new HashMap<>();
        Field[] fields = StatusController.class.getDeclaredFields();
        for (Field field : fields) {
            map.put(field.getName(), field.get(this).toString());
        }
        return map;
    }

}