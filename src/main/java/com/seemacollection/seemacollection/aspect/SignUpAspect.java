package com.seemacollection.seemacollection.aspect;

import com.seemacollection.seemacollection.entity.SignUpCountEntity;
import com.seemacollection.seemacollection.dao.AspectDao;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;

//@Component
//@Aspect
@Slf4j
public class SignUpAspect {

    @Autowired
    private AspectDao aspectDao;

    @AfterReturning("execution(public * com.seemacollection.seemacollection.service.LoginService.signUp(..))")
    public void countSignUp(){
        Date date = Date.valueOf(LocalDate.now());
        Optional<SignUpCountEntity> ent = aspectDao.findById(date);
        if (ent.isPresent()){
           ent.get().setNoOfSignUp( ent.get().getNoOfSignUp() + 1);
           aspectDao.save(ent.get());
        }else{
            SignUpCountEntity signUpCountEntity1 = new SignUpCountEntity();
            signUpCountEntity1.setNoOfSignUp(1);
            signUpCountEntity1.setDate(date);
            aspectDao.save(signUpCountEntity1);
        }
    }

    @Before("within(com.seemacollection.seemacollection..*)")
    public void logBefore(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        log.info("entry: {}", className + "." +methodName);
    }

    @After("within(com.seemacollection.seemacollection..*)")
    public void logAfter(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        log.info("exit: {}", className+"."+methodName);
    }

//    @Around("within(com.seemacollection.seemacollection.controller.LoginController)")
//    public void logTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getSignature().getDeclaringTypeName();
//        long startTime = System.nanoTime();
//        joinPoint.proceed();
//        long endTime = System.nanoTime();
//        long durationInNanoseconds = endTime - startTime;
//        log.info("Time taken by: {} is {}", durationInNanoseconds/1000000, className+"."+methodName);
//    }
}
