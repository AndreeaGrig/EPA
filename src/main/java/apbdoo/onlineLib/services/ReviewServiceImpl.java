package apbdoo.onlineLib.services;

import apbdoo.onlineLib.aspects.executiontime.TrackExecutionTime;
import apbdoo.onlineLib.domain.Review;
import apbdoo.onlineLib.repositories.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @TrackExecutionTime
    @Override
    public Review saveReview(Review review) {
        Review savedReview = reviewRepository.save(review);
        return savedReview;
    }

    @TrackExecutionTime
    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    @TrackExecutionTime
    @Override
    public Review findById(Long id) {

        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (!reviewOptional.isPresent()) {
            throw new RuntimeException("Review not found!");
        }

        return reviewOptional.get();
    }
}
