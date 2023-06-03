package md.miller1995.travelbooking.services.travels;

import md.miller1995.travelbooking.models.dtos.travels.TravelDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TravelService {

    TravelDTO saveTravel(TravelDTO travelDTO);
    TravelDTO updateTravel(UUID id, TravelDTO travelDTO);
    List<TravelDTO> findAllTravelByType(String type);
    void deleteTravel(UUID id);
    List<TravelDTO> findAllTravelByDateAndByType(String type, LocalDate startDate, LocalDate endDate);
}
