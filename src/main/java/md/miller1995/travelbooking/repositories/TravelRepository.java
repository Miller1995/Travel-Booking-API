package md.miller1995.travelbooking.repositories;

import md.miller1995.travelbooking.models.entities.travels.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TravelRepository extends JpaRepository<TravelEntity, UUID> {

    /**
     * This method find all travel after their type
     *
     * @author Anton Nirca
     * @since 03/06/2023
     * @param type
     * @return
     */
    List<TravelEntity> findTravelEntitiesByType(String type);

    /**
     * This method find all travels from database between 2 date and after their type
     *
     * @author Anton Nirca
     * @since 03/06/2023
     * @param type
     * @param startDate
     * @param endDate
     * @return TravelEntity
     */
    List<TravelEntity> findAllByTypeAndStartDateIsGreaterThanEqualAndEndDateLessThanEqual(String type, LocalDate startDate, LocalDate endDate);
}
