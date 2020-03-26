package apbdoo.onlineLib.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author alexnutu
 * @since 3/26/2020
 */

@Aspect
@Component
@Slf4j
public class ExecutionTimeAdvice {

    /**
     * Method for logging the time of any method with @TrackTime annotation
     */
    @Around("@annotation(apbdoo.onlineLib.aspects.TrackExecutionTime)")// usually we are attaching the pointcuts, this time we create an annotation for that
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // we used ProceedingJoinPoint because we want to use @Around(before and after method execution)
        Object object = pjp.proceed();
        long endTime = System.currentTimeMillis();
        log.info("=================== TIME EXECUTION ===================");
        log.info("Class:  '" + pjp.getTarget().getClass() + "'");
        log.info("Method: '" + pjp.getSignature() + "'");
        log.info("........................ " + (endTime - startTime) + "ms ........................");
        return object;
    }
}
