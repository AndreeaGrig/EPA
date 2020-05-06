package apbdoo.onlineLib.aspects.executiontime;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author alexnutu
 * @since 3/26/2020
 */

@Aspect
@Order(2)
@Component
@Slf4j
public class ExecutionTimeAdvice {

    /**
     * Method for logging the time of any method with @TrackTime annotation
     */
    // usually we are attaching the pointcuts, this time we create an annotation for that
    @Around("@annotation(apbdoo.onlineLib.aspects.executiontime.TrackExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        // we used ProceedingJoinPoint because we want to use @Around (before and after method execution)
        // we could also use @Before and @After, but this way is more clean, the before operation is only for saving the startTime
        Object object = pjp.proceed();
        long endTime = System.currentTimeMillis();
        log.info("=================== TIME EXECUTION ===================");
        log.info("--> method: '" + pjp.getSignature() + "'");
        log.info("........................ " + (endTime - startTime) + "ms ........................\n");
        return object;
    }
}
