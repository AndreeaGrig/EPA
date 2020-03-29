package apbdoo.onlineLib.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author alexnutu
 * @since 3/25/2020
 */

@Aspect
@Component
@Slf4j
public class ImageAspect {

    /**
     * Saving cover image for a book operation
     */
    @Pointcut("execution(public * saveImageFile(..))")
    public void pointcutSaveImage() {
    }

    @Before("pointcutSaveImage()")
    public void beforeSaveImageAdvice(JoinPoint joinPoint) {
        Long bookId = (Long) joinPoint.getArgs()[0];
        log.info("BEFORE: (Saving book cover image)-> Book ID: " + bookId);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning("pointcutSaveImage()")
    public void afterSuccessSaveImageAdvice(JoinPoint joinPoint) {
        Long bookId = (Long) joinPoint.getArgs()[0];
        log.info("AFTER: (Saving book cover image)-> Book ID: " + bookId);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing("pointcutSaveImage()")
    public void afterFailSaveImageAdvice(JoinPoint joinPoint) {
        log.error("AFTER: (Saving book cover image)-> FAILED to execute!");
        log.error("... " + joinPoint.getSignature());
    }
}
