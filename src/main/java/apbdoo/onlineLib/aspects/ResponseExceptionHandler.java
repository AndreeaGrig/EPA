package apbdoo.onlineLib.aspects;

import apbdoo.onlineLib.exception.AuthenticationException;
import apbdoo.onlineLib.exception.EntityNoFoundException;
import apbdoo.onlineLib.exception.FileFormatException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Aspect
@Order(3)
@Component
public class ResponseExceptionHandler {

    @AfterThrowing(value = "within(apbdoo.onlineLib.services.*)", throwing = "ex")
    public void handleEntityNotFoundException(EntityNoFoundException ex) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }

    @AfterThrowing(value = "within(apbdoo.onlineLib.services.*)", throwing = "ex")
    public void handleAuthenticationException(AuthenticationException ex) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password", ex);
    }

    @AfterThrowing(value = "within(apbdoo.onlineLib.services.*)", throwing = "ex")
    public void handleFileFormatException(FileFormatException ex) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }

}
