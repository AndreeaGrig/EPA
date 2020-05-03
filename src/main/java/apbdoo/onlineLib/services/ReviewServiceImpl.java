package apbdoo.onlineLib.services;

import apbdoo.onlineLib.aspects.executiontime.TrackExecutionTime;
import apbdoo.onlineLib.domain.Review;
import apbdoo.onlineLib.exception.EntityNoFoundException;
import apbdoo.onlineLib.repositories.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @TrackExecutionTime
    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @TrackExecutionTime
    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @TrackExecutionTime
    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNoFoundException(String.format("Could not find review by id: %s", id)));
    }
}
