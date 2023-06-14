package md.miller1995.travelbooking.services.reviews;

import md.miller1995.travelbooking.models.dtos.reviews.ReviewDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ReviewService {

    /**
     *
     * @param id
     * @return Optional
     */
    ReviewDTO getReviewsById(UUID id);

    /**
     *
     * @param reviewDTO
     * @return
     */
    ReviewDTO addReview(ReviewDTO reviewDTO);

    /**
     *
     * @param id
     */
    void deleteReviewById(UUID id);

    /**
     *
     * @param place
     * @return
     */
    List<ReviewDTO> getAllReviewsByPlace(String place);
}
