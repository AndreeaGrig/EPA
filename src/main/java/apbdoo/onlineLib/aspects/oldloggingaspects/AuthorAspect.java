//package apbdoo.onlineLib.aspects.oldaspects;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//
//
///**
// * @author alexnutu
// * @since 3/25/2020
// */
//
//@Aspect
//@Component
//@Slf4j
//public class AuthorAspect {
//
//    /**
//     * Getting the authors operation
//     */
//    @Pointcut("execution(public * getAuthors(..))")
//    public void pointcutGetAuthors() {
//    }
//
//    @Before("pointcutGetAuthors()")
//    public void beforeGetAuthorsAdvice(JoinPoint joinPoint) {
//        log.info("BEFORE: (Getting all authors)");
//        log.info("... " + joinPoint.getSignature());
//    }
//
//    @AfterReturning(value = "pointcutGetAuthors()", returning = "returnValue")
//    public void afterSuccessGetAuthorsAdvice(JoinPoint joinPoint, Object returnValue) {
//        Set authorsSet = (Set) returnValue;
//        log.info("AFTER: (Getting all authors)-> '" + authorsSet.size() + "' authors");
//        log.info("... " + joinPoint.getSignature());
//    }
//
//    @AfterThrowing("pointcutGetAuthors()")
//    public void afterFailGetAuthorsAdvice(JoinPoint joinPoint) {
//        log.error("AFTER: (Getting all authors)-> FAILED to execute!");
//        log.error("... " + joinPoint.getSignature());
//    }
//}