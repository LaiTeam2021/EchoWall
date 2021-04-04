package com.laiteam.echowall.service;


import com.laiteam.echowall.dal.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface JwtService {
    String toToken(User user);

    String to15MinToken(User user);

    Optional<String> getSubFromToken(String token);
}
