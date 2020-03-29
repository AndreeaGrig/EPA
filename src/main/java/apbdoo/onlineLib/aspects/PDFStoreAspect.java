package apbdoo.onlineLib.aspects;

import apbdoo.onlineLib.domain.Book;
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
public class PDFStoreAspect {

    /**
     * Saving a PDF operation
     */
    @Pointcut("execution(public * savePdfFile(..))")
    public void pointcutSaveBookPDF() {
    }

    @Before("pointcutSaveBookPDF()")
    public void beforeSaveBookPDFAdvice(JoinPoint joinPoint) {
        Long bookId = (Long) joinPoint.getArgs()[0];
        log.info("BEFORE: (Saving book PDF)-> book ID: " + bookId);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning("pointcutSaveBookPDF()")
    public void afterSuccessSaveBookPDFAdvice(JoinPoint joinPoint) {
        Long bookId = (Long) joinPoint.getArgs()[0];
        log.info("AFTER: (Saving book PDF)-> book ID: " + bookId);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing("pointcutSaveBookPDF()")
    public void afterFailSaveBookAdvice(JoinPoint joinPoint) {
        log.error("AFTER: (Saving book PDF)-> FAILED to execute!");
        log.error("... " + joinPoint.getSignature());
    }
}
