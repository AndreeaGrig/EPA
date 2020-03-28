package apbdoo.onlineLib.aspects;

import apbdoo.onlineLib.domain.Book;
import apbdoo.onlineLib.domain.Favourites;
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
public class FavouritesAspect {

    /**
     * Saving favourites books operation
     */
    @Pointcut("execution(public * saveFavourites(..))")
    public void pointcutSaveFavourites() {
    }

    @Before(value = "pointcutSaveFavourites()")
    public void beforeSaveFavouritesAdvice(JoinPoint joinPoint) {
        Favourites favourite = (Favourites) joinPoint.getArgs()[0];
        log.info("BEFORE: (Saving favourite book)-> User: " + favourite.getUserFav().getUsername()
                + ", Book: " + favourite.getBookFav().getTitle());
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutSaveFavourites()", returning = "returnValue")
    public void afterSuccessSaveFavouritesAdvice(JoinPoint joinPoint, Object returnValue) {
        Favourites returnedFavourite = (Favourites) returnValue;
        log.info("AFTER: (Saving favourite book)-> Favourites ID: " + returnedFavourite.getId());
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing("pointcutSaveFavourites()")
    public void afterFailSaveFavouritesAdvice(JoinPoint joinPoint) {
        log.error("AFTER: (Saving favourite book)-> FAILED to execute!");
        log.error("... " + joinPoint.getSignature());
    }

    /**
     * Delete favourites books operation
     */
    @Pointcut("execution(public * apbdoo.onlineLib.services.FavouritesServiceImpl.deleteById(..))")
    public void pointcutDeleteFavouriteById() {
    }

    @Before(value = "pointcutDeleteFavouriteById()")
    public void beforeDeleteFavouriteByIdAdvice(JoinPoint joinPoint) {
        Long favouritesId = (Long) joinPoint.getArgs()[0];
        log.info("BEFORE: (Deleting favourite book)-> Favourite ID: " + favouritesId);
        log.info("... " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcutDeleteFavouriteById()")
    public void afterDeleteFavouriteBookSuccessAdvice(JoinPoint joinPoint) {
        log.info("AFTER: (Deleting book from favourites)-> Success!");
        log.info("... " + joinPoint.getSignature());
    }

    @AfterThrowing(value = "pointcutDeleteFavouriteById()")
    public void afterDeleteFavouriteBookAdvice(JoinPoint joinPoint) {
        Long favouritesId = (Long) joinPoint.getArgs()[0];
        log.error("AFTER: (Deleting book from favourites)-> Favourite ID: " + favouritesId + " not found-> Failed!");
        log.error("... " + joinPoint.getSignature());
    }
}
