package com.laiteam.echowall.sal.firestoreDB;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class Mail {
    Map<String, Object> delivery;
    Map<String, Object> message;
    String to;
}
