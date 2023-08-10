package net.therap.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author tanvirtareq
 * @since 8/9/23
 */
@Aspect
@Component
public class LoggingAdvice {

    private static final Logger LOGGER = Logger.getLogger(LoggingAdvice.class);

    @Before("execution(* net.therap.controller..*(..) ) || execution(* net.therap.service..*(..) )")
    public void logMethodCall(JoinPoint joinPoint) {
        LOGGER.debug("Calling method " + joinPoint.getSignature());
    }
}