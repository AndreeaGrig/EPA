package apbdoo.onlineLib.aspects;

import apbdoo.onlineLib.domain.Review;
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
public class ReviewAspect {

    /**
     * Saving a review operation
     */
    @Pointcut("execution(public * saveReview(..))")
    public void pointcutSaveReview() {
    }

    @Before("pointcutSaveReview()")
    public void beforeSaveReviewAdvice(JoinPoint joinPoint) {
        Review review = (Review) joinPoint.getArgs()[0];
        log.info("BEFORE: (Saving review)-> User: '" + review.getUser().getUsername()
                + "', Book: '" + review.getBook().getTitle() + "'");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutSaveReview()", returning = "returnValue")
    public void afterSuccessSaveReviewAdvice(JoinPoint joinPoint, Object returnValue) {
        Review review = (Review) joinPoint.getArgs()[0];
        log.info("AFTER: (Saving review)-> Review ID: " + review.getId());
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing("pointcutSaveReview()")
    public void afterFailSaveReviewAdvice(JoinPoint joinPoint) {
        log.error("AFTER: (Saving review)-> FAILED to execute!");
        log.error("... " + joinPoint.getSignature());
    }

    /**
     * Deleting review by id operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.ReviewServiceImpl.deleteById(..))")
    public void pointcutDeletingReview() {
    }

    @Before("pointcutDeletingReview()")
    public void beforeDeleteReviewAdvice(JoinPoint joinPoint) {
        Long reviewId = (Long) joinPoint.getArgs()[0];
        log.info("BEFORE: (Deleting the review)-> Review ID: " + reviewId);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutDeletingReview()")
    public void afterDeleteReviewSuccessAdvice(JoinPoint joinPoint) {
        log.info("AFTER: (Deleting the review)-> SUCCESS!");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing(value = "pointcutDeletingReview()")
    public void afterDeleteReviewFailAdvice(JoinPoint joinPoint) {
        Long reviewId = (Long) joinPoint.getArgs()[0];
        log.error("AFTER: (Deleting the review)-> Review ID: " + reviewId + " not found-> FAILED!");
        log.error("... " + joinPoint.getSignature());
    }

    /**
     * Getting review by id operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.ReviewServiceImpl.findById(..))")
    public void pointcutGetOneReview() {
    }

    @Before("pointcutGetOneReview()")
    public void beforeGetOneReviewAdvice(JoinPoint joinPoint) {
        Long reviewId = (Long) joinPoint.getArgs()[0];
        log.info("BEFORE: (Getting the review)-> Review ID: " + reviewId);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutGetOneReview()", returning = "returnValue")
    public void afterGetOneReviewSuccessAdvice(JoinPoint joinPoint, Object returnValue) {
        Review returnedReview = (Review) returnValue;
        log.info("AFTER: (Getting the review)-> Review ID: "
                + returnedReview.getId() + ", Text: '" + returnedReview.getText() + "'");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing(value = "pointcutGetOneReview()")
    public void afterGetOneReviewFailAdvice(JoinPoint joinPoint) {
        Long reviewId = (Long) joinPoint.getArgs()[0];
        log.error("AFTER: (Getting the review)-> Review ID: " + reviewId + " not found-> FAILED!");
        log.error("... " + joinPoint.getSignature());
    }
}
