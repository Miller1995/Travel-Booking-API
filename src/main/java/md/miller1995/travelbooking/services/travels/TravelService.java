package md.miller1995.travelbooking.services.travels;

import md.miller1995.travelbooking.models.dtos.travels.TravelDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TravelService {

    /**
     *  This method get an object of type TravelDTO after that,
     *  converted this object in object of type TravelEntity and save it in database
     *
     * @author Anton Nirca
     * @since 03/06/2023
     * @param travelDTO
     * @return object of type TravelDTO
     */
    TravelDTO saveTravel(TravelDTO travelDTO);

    /**
     * This method get an object of type TravelDTO after that,
     * the method is updating this TravelDTO, after converted its in object of type TravelEntity and save in database
     *
     * @author Anton Nirca
     * @since 03/06/2023
     * @param id
     * @param travelDTO
     * @return object of type TravelDTO
     */
    TravelDTO updateTravel(UUID id, TravelDTO travelDTO);

    /**
     * This method find all travels from database after their type
     *
     * @author Anton Nirca
     * @since 03/06/2023
     * @param type
     * @return  list of object type TravelDTO
     */
    List<TravelDTO> findAllTravelByType(String type);

    /**
     * This method delete travel by id travel
     *
     * @author Anton Nirca
     * @since 03/06/2023
     * @param id
     */
    void deleteTravel(UUID id);

    /**
     * This method find all travels from database between 2 date and after their type
     *
     * @author Anton Nirca
     * @since 03/06/2023
     * @param type
     * @param startDate
     * @param endDate
     * @return list of object type TravelDTO
     */
    List<TravelDTO> findAllTravelByDateAndByType(String type, LocalDate startDate, LocalDate endDate);
}
