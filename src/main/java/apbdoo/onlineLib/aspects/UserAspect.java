package apbdoo.onlineLib.aspects;

import apbdoo.onlineLib.domain.User;
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
public class UserAspect {

    /**
     * Getting user by email operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.UserServiceImpl.findByEmail(..))")
    public void pointcutGetUserByEmail() {
    }

    @Before("pointcutGetUserByEmail()")
    public void beforeGetUserByEmailAdvice(JoinPoint joinPoint) {
        String email = (String) joinPoint.getArgs()[0];
        log.info("BEFORE: (Getting user by email)-> E-mail: '" + email + "'");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutGetUserByEmail()", returning = "returnValue")
    public void afterGetUserByEmailSuccessAdvice(JoinPoint joinPoint, Object returnValue) {
        User returnedUser = (User) returnValue;
        log.info("AFTER: (Getting user by email)-> Username: '" + returnedUser.getUsername() + "'");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing(value = "pointcutGetUserByEmail()")
    public void afterGetUserByEmailFailAdvice(JoinPoint joinPoint) {
        String email = (String) joinPoint.getArgs()[0];
        log.error("AFTER: (Getting user by email)-> E-mail: '" + email + "' not found-> FAILED!");
        log.error("... " + joinPoint.getSignature());
    }

    /**
     * Saving a user operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.UserServiceImpl.save(..))")
    public void pointcutSaveUser() {
    }

    @Before("pointcutSaveUser()")
    public void beforeSaveUserAdvice(JoinPoint joinPoint) {
        User user = (User) joinPoint.getArgs()[0];
        log.info("BEFORE: (Saving user)-> E-mail: '" + user.getEmail()
                + "', Role: '" + "ROLE_USER'");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutSaveUser()", returning = "returnValue")
    public void afterSuccessSaveUserAdvice(JoinPoint joinPoint, Object returnValue) {
        User savedUser = (User) returnValue;
        log.info("AFTER: (Saving user)-> User ID: " + savedUser.getId());
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing("pointcutSaveUser()")
    public void afterFailSaveUserAdvice(JoinPoint joinPoint) {
        log.error("AFTER: (Saving user)-> FAILED to execute!");
        log.error("... " + joinPoint.getSignature());
    }

    /**
     * Getting user by username operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.UserServiceImpl.findByEmail(..))")
    public void pointcutGetUserByUsername() {
    }

    @Before("pointcutGetUserByUsername()")
    public void beforeGetUserByUsernameAdvice(JoinPoint joinPoint) {
        String username = (String) joinPoint.getArgs()[0];
        log.info("BEFORE: (Getting user by username)-> Username: '" + username + "'");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutGetUserByUsername()", returning = "returnValue")
    public void afterGetUserByUsernameSuccessAdvice(JoinPoint joinPoint, Object returnValue) {
        User returnedUser = (User) returnValue;
        log.info("AFTER: (Getting user by username)-> User ID: '" + returnedUser.getId() + "'");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing(value = "pointcutGetUserByUsername()")
    public void afterGetUserByUsernameFailAdvice(JoinPoint joinPoint) {
        String username = (String) joinPoint.getArgs()[0];
        log.error("AFTER: (Getting user by username)-> Username: '" + username + "' not found-> FAILED!");
        log.error("... " + joinPoint.getSignature());
    }
}
