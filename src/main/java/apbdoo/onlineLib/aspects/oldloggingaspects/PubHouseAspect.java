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
///**
// * @author alexnutu
// * @since 3/25/2020
// */
//
//@Aspect
//@Component
//@Slf4j
//public class PubHouseAspect {
//
//    /**
//     * Getting all pubHouses operation
//     */
//    @Pointcut("execution(public * getPubHouses(..))")
//    public void pointcutGetPubHouses() {
//    }
//
//    @Before("pointcutGetPubHouses()")
//    public void beforeGetPubHousesAdvice(JoinPoint joinPoint) {
//        log.info("BEFORE: (Getting all publication houses)");
//        log.info("... " + joinPoint.getSignature());
//    }
//
//    @AfterReturning(value = "pointcutGetPubHouses()", returning = "returnValue")
//    public void afterGetPubHousesSuccessAdvice(JoinPoint joinPoint, Object returnValue) {
//        Set pubHousesSet = (Set) returnValue;
//        log.info("AFTER: (Getting all publication houses)-> '" + pubHousesSet.size() + "' publication houses");
//        log.info("... " + joinPoint.getSignature());
//    }
//
//    @AfterThrowing("pointcutGetPubHouses()")
//    public void afterGetPubHousesFailAdvice(JoinPoint joinPoint) {
//        log.info("AFTER: (Getting all publication houses)-> FAILED to execute!");
//        log.info("... " + joinPoint.getSignature());
//    }
//}
