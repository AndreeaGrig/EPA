package apbdoo.onlineLib.aspects;

import apbdoo.onlineLib.exceptions.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Order(3)
@Component
@Slf4j
public class AuthorizationAspect {

    @Pointcut("execution(* apbdoo.onlineLib.controllers.AddBookController.*(..)) || execution(* apbdoo.onlineLib.controllers.PDFStoreController.*(..)) || execution(* apbdoo.onlineLib.controllers.ImageController.showUploadForm(..))")
    public void pointcutAuthorizeAdmin() {}

    @Pointcut("execution(* apbdoo.onlineLib.controllers.FavouritesController.*(..))")
    public void pointcutAuthorizeUser() {}

    @Pointcut("execution(* apbdoo.onlineLib.controllers.ReviewController.updateReviewContent(..)) || execution(* apbdoo.onlineLib.controllers.ReviewController.addReview(..)) || execution(* apbdoo.onlineLib.controllers.ReviewController.deleteReviewById(..))")
    public void pointcutAuthorizeCommon() {}

    @Before("pointcutAuthorizeAdmin()")
    public void beforeAdminRequestsAdvice(JoinPoint joinPoint) throws ForbiddenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null)
            if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
                throw new ForbiddenException("Exception");
    }

    @Before("pointcutAuthorizeUser()")
    public void beforeLoggedUserRequestsAdvice(JoinPoint joinPoint) throws ForbiddenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null)
            if(!authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")))
                throw new ForbiddenException("Exception");
    }

    @Before("pointcutAuthorizeCommon()")
    public void beforeAuthorizedRequestsAdvice(JoinPoint joinPoint) throws ForbiddenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null)
            if(!(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")) ||
                    authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                throw new ForbiddenException("Exception");
    }
}
