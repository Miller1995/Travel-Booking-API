package md.miller1995.travelbooking.repositories;

import md.miller1995.travelbooking.models.entities.reviews.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {

    /**
     * @author Anton Nirca
     * @since 14/06/2023
     *
     * @param id
     * @return ReviewEntity
     */
    Optional<ReviewEntity> findReviewEntitiesById(UUID id);

    /**
     *
     * @author Anton Nirca
     * @since 14/06/2023
     *
     * @param place
     * @return List of ReviewEntity
     */
    List<ReviewEntity> findReviewEntitiesByPlace(String place);
}
