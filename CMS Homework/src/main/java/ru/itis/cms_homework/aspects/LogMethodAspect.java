package ru.itis.cms_homework.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.itis.cms_homework.security.details.AccountUserDetails;

import java.util.Date;

@Component
@Slf4j
@Aspect
public class LogMethodAspect {

    @Around("@annotation(ru.itis.cms_homework.aspects.annotations.LogMethod)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long accountId = ((AccountUserDetails) auth.getPrincipal()).getAccount().getId();

        log.info("Name of the executed method: " + joinPoint.getSignature().getName());
        log.info("Time: " + new Date().toString());
        log.info("Args of the method: " + auth.getName() + ", " + accountId);
        log.info("Id of current authenticated user: " + accountId);

        return joinPoint.proceed();
    }
}
