package md.miller1995.travelbooking.repositories;

import md.miller1995.travelbooking.models.entities.travels.TravelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TravelRepository extends JpaRepository<TravelEntity, UUID> {

    List<TravelEntity> findTravelEntitiesByType(String type);

    List<TravelEntity> findAllByTypeAndStartDateIsGreaterThanEqualAndEndDateLessThanEqual(String type, LocalDate startDate, LocalDate endDate);

}
