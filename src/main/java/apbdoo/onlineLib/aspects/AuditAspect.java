package apbdoo.onlineLib.aspects;

import apbdoo.onlineLib.utilities.AuditableObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

@Aspect
@Order(1)
@Component
@Slf4j
public class AuditAspect {

    @Pointcut("execution(* apbdoo.onlineLib.repositories..save*(..))")
    public void pointcutSavingEntity() {}

    @Before("pointcutSavingEntity()")
    public void beforeSavingEntityAdvice(JoinPoint joinPoint)
    {
        log.info("Saving");
        final String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        if (methodName.startsWith("save") && isNotEmpty(arguments))
        {
            if ("save".equals(methodName))
            {
                Object persistentObject = arguments[0];
                audit(persistentObject);
            }
            else if ("saveAll".equals(methodName))
            {
                Iterable iterable = (Iterable) arguments[0];
                iterable.forEach(this::audit);
            }
        }
    }

    private boolean isNotEmpty(Object... objs)
    {
        return objs != null && objs.length > 0;
    }

    private void audit(Object persistentObject)
    {
        if (persistentObject instanceof AuditableObject)
        {
            AuditableObject object = (AuditableObject) persistentObject;
            final Date currentDate = new Date();

            Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
            final String username = currentUser.getName();

            if (object.getDateCreated() == null)
            {
                object.setCreatedByUser(username);
                object.setDateCreated(currentDate);
            }

            object.setLastModifyingUser(username);
            object.setDateLastModified(currentDate);
        }
    }







}
