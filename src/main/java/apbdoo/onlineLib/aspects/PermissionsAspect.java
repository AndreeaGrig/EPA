package apbdoo.onlineLib.aspects;


import apbdoo.onlineLib.exceptions.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Slf4j
@Aspect
@Order(1)
@Component
public class PermissionsAspect {

    @Before("execution(* apbdoo.onlineLib.controllers.*.*(..)) && (args( model,..)) )")
    public void beforePermissionsAdvice(JoinPoint joinPoint, Model model) throws ForbiddenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
            model.addAttribute("isAdmin", true);
        else
            model.addAttribute("isAdmin", false);
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")))
            model.addAttribute("isLoggedUser", true);
        else
            model.addAttribute("isLoggedUser", false);
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS")))
            model.addAttribute("isAuthenticated", false);
        else
            model.addAttribute("isAuthenticated", true);
    }

}
