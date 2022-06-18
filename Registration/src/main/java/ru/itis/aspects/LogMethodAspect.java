package ru.itis.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LogMethodAspect {

    @Around("@annotation(ru.itis.aspects.annotations.LogMethod)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("works good");
        log.info("Name of the executed method: " + joinPoint.getSignature().getName());

        StringBuilder sb = new StringBuilder();
        for (Object o: joinPoint.getArgs()){
            sb.append(o.toString() + " ; ");
        }

        log.info("Agrs of the method: " + sb.toString());

        long start = System.currentTimeMillis();
        Object returnObject = joinPoint.proceed();
        long end = System.currentTimeMillis();

        log.info("Time of executing method: " + (end - start)/1000 + "seconds");

        return returnObject;
    }
}
