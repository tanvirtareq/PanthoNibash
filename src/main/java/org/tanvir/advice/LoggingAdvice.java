package org.tanvir.advice;

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

    @Before("execution(* org.tanvir.controller..*(..) ) || execution(* org.tanvir.service..*(..) )")
    public void logMethodCall(JoinPoint joinPoint) {
        LOGGER.debug("Calling method " + joinPoint.getSignature());
    }
}