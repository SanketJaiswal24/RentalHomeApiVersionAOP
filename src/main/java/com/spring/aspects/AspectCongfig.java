package com.spring.aspects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AspectCongfig {

    private Log log = LogFactory.getLog(this.getClass());

   /* @Pointcut("execution(* com.journaldev.spring.controllers.AddPropertyController.addProperty(..))")
    protected void loggingOperation() {}*/

    @Before("execution(* com.journaldev.spring.controllers.*.*(..))")
    @Order(1)
    public void logJoinPoint(JoinPoint joinPoint)
    {
        log.info("Join point kind : " + joinPoint.getKind());
        log.info("Signature declaring type : "+ joinPoint.getSignature().getDeclaringTypeName());
        log.info("Signature name : " + joinPoint.getSignature().getName());
        log.info("Arguments : " + Arrays.toString(joinPoint.getArgs()));
        log.info("Target class : "+ joinPoint.getTarget().getClass().getName());
        log.info("This class : " + joinPoint.getThis().getClass().getName());
    }

    @AfterReturning(pointcut="execution(* com.journaldev.spring.controllers.*.*(..))", returning = "result")
    @Order(2)
    public void logAfter(JoinPoint joinPoint, Object result)
    {
        log.info("Exiting from Method :"+joinPoint.getSignature().getName());
        log.info("Return value :"+result);
    }

    @AfterThrowing(pointcut="execution(* com.journaldev.spring.controllers.*.*(..))", throwing = "e")
    @Order(3)
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e)
    {
        log.error("An exception has been thrown in "+ joinPoint.getSignature().getName() + "()");
        log.error("Cause :"+e.getCause());
    }

    @Around("execution(* com.journaldev.spring.controllers.*.*(..))")
    @Order(4)
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable
    {
        log.info("The method " + joinPoint.getSignature().getName()+ "() begins with " + Arrays.toString(joinPoint.getArgs()));
        try
        {
            Object result = joinPoint.proceed();
            log.info("The method " + joinPoint.getSignature().getName()+ "() ends with " + result);
            return result;
        } catch (IllegalArgumentException e)
        {
            log.error("Illegal argument "+ Arrays.toString(joinPoint.getArgs()) + " in "+ joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }
}