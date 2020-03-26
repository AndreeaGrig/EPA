package apbdoo.onlineLib.aspects;

import apbdoo.onlineLib.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
public class BookServiceAspect {

    @Pointcut("execution(public * saveBook(..))")
    public void pointcutSaveBookService() {
    }

    @Pointcut("execution(public * apbdoo.onlineLib.controllers.AddBookController.saveOrUpdate(..))")
    public void pointcutSaveBookController() {
    }

    @Pointcut("pointcutSaveBookService() || pointcutSaveBookController()")
    private void pointCutsSaveBook() {
    }

    @Before("pointCutsSaveBook()")
    public void beforeSaveBookAdvice(JoinPoint joinPoint) {
        Book book = (Book) joinPoint.getArgs()[0];
        log.info("Before:");
        log.info("... " + joinPoint.getSignature());
    }

    @After("pointCutsSaveBook()")
    public void afterSaveBookAdvice(JoinPoint joinPoint) {
        Book book = (Book) joinPoint.getArgs()[0];
        log.info("After: (saved ID: '" + book.getId() + ")");
        log.info("... " + joinPoint.getSignature());
    }
}
