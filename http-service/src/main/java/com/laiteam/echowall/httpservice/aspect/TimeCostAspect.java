package com.laiteam.echowall.httpservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Aspect
@Component
public class TimeCostAspect {
    private static final Integer MAX_RESULT_LOG_LENGTH = 200;

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @Around("execution(* com.laiteam.echowall.httpservice.api.*.*(..))")
    public Object handleHttpServiceMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("TimeCostAspect, invoke {}", pjp.getSignature().getName());
        long start = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        List<String> argsList = new ArrayList<>();
        for (Object arg : args) {
            if (arg != null) {
                argsList.add(arg.toString());
            }
        }
        Object object = pjp.proceed();
        String resultStr = object.toString();

        log.info("Environment: {}", springProfilesActive);
        log.info("Parameter: {}", String.join(" , ", argsList));
        log.info("Result: {}", resultStr.length() > MAX_RESULT_LOG_LENGTH ? resultStr
                .substring(0, MAX_RESULT_LOG_LENGTH) : resultStr);
        log.info(
                pjp.getSignature().getName() + " time cost：" + (System.currentTimeMillis() - start) + " ms");

        return object;
    }
}