package com.seemacollection.seemacollection.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceAspect {

    @Before("@within(org.springframework.stereotype.Service)")
    public void doLog(JoinPoint joinPoint){
        String className = joinPoint.getSignature().getDeclaringTypeName();
        log.info("inside : {}", className);
    }
}
