package com.telusko.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// This class is an Aspect, which allows us to add cross-cutting concerns
@Aspect
// This annotation makes the class a Spring-managed component
@Component
public class ExceptionLoggingAspect {

    // Create a logger instance for this class
    private static final Logger logger = LoggerFactory.getLogger(ExceptionLoggingAspect.class);

    // Define a pointcut that targets all methods in classes annotated with @RestController
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointcut() {
        // This method is empty because it's just used to define the pointcut
    }

    // This advice will run after an exception is thrown from any method matching the controllerPointcut
    @AfterThrowing(pointcut = "controllerPointcut()", throwing = "ex")
    public void logException(Exception ex) {
        // Log the caught exception using the error level
        logger.error("Exception caught: ", ex);
    }
}