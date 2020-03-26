package apbdoo.onlineLib.services;

import apbdoo.onlineLib.aspects.TrackExecutionTime;
import apbdoo.onlineLib.domain.Favourites;
import apbdoo.onlineLib.repositories.FavouritesRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FavouritesServiceImpl implements FavouritesService {

    @Autowired
    FavouritesRepository favouritesRepository;

    @TrackExecutionTime
    @Override
    public Favourites saveFavourites(Favourites favourite) {
        List<Favourites> favs = favouritesRepository.findByBookAndUser(favourite.getBookFav(), favourite.getUserFav());
        //TODO:
//        log.info("Saving book "+favourite.getBookFav().getTitle()+" in "+favourite.getUserFav().getUsername()+"'s list of favourites");
        if(favs.size() == 0)
            return favouritesRepository.save(favourite);
        else return favs.get(0);
    }

    @TrackExecutionTime
    @Override
    public void deleteById(Long id) {
        //TODO:
//        log.info("Deleting favourite with id: " + id);
        favouritesRepository.deleteById(id);
    }
}
