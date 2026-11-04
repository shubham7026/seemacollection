package com.seemacollection.seemacollection.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
@Slf4j
public class ServiceAspect {

    private String className;

    // @within works when annotation is on class or if you want all the methods of the class will get intercepted
    @Before("@within(org.springframework.stereotype.Service)")
    public void doLog(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        log.info("inside : {}", className);
    }

    // @annotation intercepts methods which are annotated with some annotation
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void interceptPostAPICalls(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        log.info("interception: {} api", className + "." + methodName);
    }

    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void beginTrx(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        log.info("Begin Trx: {} by thread : {}", className + "." + methodName,  Thread.currentThread().getName());
        joinPoint.proceed();
        log.info("Begin Trx: {} by thread : {}", className + "." + methodName,  Thread.currentThread().getName());
    }

//    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
//    public void endTrx(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getSignature().getDeclaringTypeName();
//        log.info("End Trx: {} by thread : {}", className + "." + methodName,  Thread.currentThread().getName());
//    }

//    @Before("args(int)")
//    public void args(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getSignature().getDeclaringTypeName();
//        log.info("args interception: {}", className + "." + methodName);
//    }
}
