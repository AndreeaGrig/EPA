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

import java.util.Set;

/**
 * @author alexnutu
 * @since 3/25/2020
 */

@Aspect
@Component
@Slf4j
public class BookServiceAspect {

    /**
     * Saving a book operation
     */
    @Pointcut("execution(public * saveBook(..))")
    public void pointcutSaveBook() {
    }

    @Before("pointcutSaveBook()")
    public void beforeSaveBookAdvice(JoinPoint joinPoint) {
        Book book = (Book) joinPoint.getArgs()[0];
        log.info("BEFORE: (Saving book)-> " + book.getTitle());
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning("pointcutSaveBook()")
    public void afterSuccessSaveBookAdvice(JoinPoint joinPoint) {
        Book book = (Book) joinPoint.getArgs()[0];
        log.info("AFTER: (Saving book)-> Book ID: " + book.getId());
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing("pointcutSaveBook()")
    public void afterFailSaveBookAdvice(JoinPoint joinPoint) {
        log.error("AFTER: (Saving book)-> FAILED to execute!");
        log.error("... " + joinPoint.getSignature());
    }

    /**
     * Getting all sorted books operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.BookServiceImpl.getBooks(..))")
    public void pointcutGetBooks() {
    }

    @Before("pointcutGetBooks()")
    public void beforeGetBooksAdvice(JoinPoint joinPoint) {
        log.info("BEFORE: (Getting all books sorted)");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutGetBooks()", returning = "returnValue")
    public void afterGetBooksAdvice(JoinPoint joinPoint, Object returnValue) {
        Set booksSet = (Set) returnValue;
        log.info("AFTER: (Getting all books sorted)-> '" + booksSet.size() + "' books");
        log.info("... " + joinPoint.getSignature());
    }

    /**
     * Getting book by id operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.BookServiceImpl.findBookById(..))")
    public void pointcutGetOneBook() {
    }

    @Before("pointcutGetOneBook()")
    public void beforeGetOneBookAdvice(JoinPoint joinPoint) {
        Long bookId = (Long) joinPoint.getArgs()[0];
        log.info("BEFORE: (Getting the book)-> Book ID: " + bookId);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutGetOneBook()", returning = "returnValue")
    public void afterGetOneBookSuccessAdvice(JoinPoint joinPoint, Object returnValue) {
        Book returnedBook = (Book) returnValue;
        log.info("AFTER: (Getting the book)-> Book ID: " + returnedBook.getId() + ", Title: " + returnedBook.getTitle());
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing(value = "pointcutGetOneBook()")
    public void afterGetOneBookFailAdvice(JoinPoint joinPoint) {
        Long bookId = (Long) joinPoint.getArgs()[0];
        log.error("AFTER: (Getting the book)-> Book ID: " + bookId + " not found-> Failed");
        log.error("... " + joinPoint.getSignature());
    }

    /**
     * Deleting book by id operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.BookServiceImpl.deleteBookById(..))")
    public void pointcutDeletingBook() {
    }

    @Before("pointcutDeletingBook()")
    public void beforeDeleteBookAdvice(JoinPoint joinPoint) {
        Long bookId = (Long) joinPoint.getArgs()[0];
        log.info("BEFORE: (Deleting the book)-> Book ID: " + bookId);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutDeletingBook()")
    public void afterDeleteBookSuccessAdvice(JoinPoint joinPoint) {
        log.info("AFTER: (Deleting the book)-> Success!");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing(value = "pointcutDeletingBook()")
    public void afterDeleteBookAdvice(JoinPoint joinPoint) {
        Long bookId = (Long) joinPoint.getArgs()[0];
        log.error("AFTER: (Deleting the book)-> Book ID: " + bookId + " not found-> Failed!");
        log.error("... " + joinPoint.getSignature());
    }

    /**
     * Getting a page with books operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.BookServiceImpl.getPage(..))")
    public void pointcutGetBooksPage() {
    }

    @Before("pointcutGetBooksPage()")
    public void beforeGetBooksPageAdvice(JoinPoint joinPoint) {
        log.info("BEFORE: (Getting books from all categories)");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning("pointcutGetBooksPage()")
    public void afterGetBooksPageAdvice(JoinPoint joinPoint) {
        log.info("AFTER: (Getting the books from all categories)-> Success!");
        log.info("... " + joinPoint.getSignature());
    }

    /**
     * Getting the category page operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.BookServiceImpl.getCategoryPage(..))")
    public void pointcutGetCategoryPage() {
    }

    @Before("pointcutGetCategoryPage()")
    public void beforeGetCategoryPageAdvice(JoinPoint joinPoint) {
        String category = joinPoint.getArgs()[0].toString();
        log.info("BEFORE: (Getting books from category)-> ID: " + category);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning("pointcutGetCategoryPage()")
    public void afterGetCategoryPageAdvice(JoinPoint joinPoint) {
        String category = joinPoint.getArgs()[0].toString();
        log.info("AFTER: (Getting books from category)-> ID: " + category + "-> Success!");
        log.info("... " + joinPoint.getSignature());
    }
}
