package apbdoo.onlineLib.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Set;

/**
 * @author alexnutu
 * @since 3/31/2020
 */

@Aspect
@Component
@Slf4j
public class ServiceLoggingAspect {

    /**
     * Logging all operations of the classes found in 'services' package
     */

    @Pointcut("within(apbdoo.onlineLib.services.*)")
    public void pointcutServices() {
    }

    // Before method call logging
    @Before("pointcutServices()")
    public void beforeMethodCallAdvice(JoinPoint joinPoint) {
        log.info("BEFORE:  " + joinPoint.getSignature());
        Object[] params = joinPoint.getArgs();
        if (!ObjectUtils.isEmpty(params)) {
            log.info("..........  Param value= ' " + params[0].toString() + " '");
        }
    }

    // After Success method call logging
    @AfterReturning(value = "pointcutServices()", returning = "returnValue")
    public void afterSuccessMethodCallAdvice(JoinPoint joinPoint, Object returnValue) {
        log.info("AFTER: " + joinPoint.getSignature());
        if (returnValue instanceof Set) {
            log.info(".......... Count= ' " + ((Set) returnValue).size() + " ' elements");
        } else if (returnValue instanceof List) {
            log.info(".......... Count= ' " + ((List) returnValue).size() + " ' elements");
        } else if (!ObjectUtils.isEmpty(returnValue)) {
            log.info(".......... Returned= ' " + returnValue.toString() + " '");
        } else {
            log.info(".......... SUCCESS!");
        }
    }

    // After Failed method call logging
    @AfterThrowing(value = "pointcutServices()", throwing = "ex")
    public void afterFailMethodCallAdvice(JoinPoint joinPoint, Exception ex) {
        log.error("AFTER: " + joinPoint.getSignature());
        Object[] params = joinPoint.getArgs();
        if (!ObjectUtils.isEmpty(params)) {
            log.error("..........  Param value= ' " + params[0].toString() + " '");
        }
        log.error(".......... FAILED! Message= ' " + ex.getMessage() + " '");
    }
}
