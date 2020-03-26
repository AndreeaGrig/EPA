package apbdoo.onlineLib.services;

import apbdoo.onlineLib.aspects.TrackExecutionTime;
import apbdoo.onlineLib.domain.Review;
import apbdoo.onlineLib.repositories.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @TrackExecutionTime
    @Override
    public Review saveReview(Review review) {
        //TODO:
//        log.info("Saving "+review.getUser().getUsername()+"'s review for book "+review.getBook().getTitle());
        Review savedReview = reviewRepository.save(review);
        return savedReview;
    }

    @TrackExecutionTime
    @Override
    public void deleteById(Long id) {
        //TODO:
//        log.info("Deleting review with id: "+id);
        reviewRepository.deleteById(id);
    }

    @TrackExecutionTime
    @Override
    public Review findById(Long id) {

        Optional<Review> reviewOptional = reviewRepository.findById(id);
        //TODO:
//        log.info("Retrieving review with id: "+id);
        if (!reviewOptional.isPresent()) {
            //TODO:
//            log.error("Review with id "+id+" not found!");
            throw new RuntimeException("Review not found!");
        }

        return reviewOptional.get();
    }
}
