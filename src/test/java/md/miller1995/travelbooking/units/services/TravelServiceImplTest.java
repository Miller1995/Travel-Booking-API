package md.miller1995.travelbooking.units.services;

import md.miller1995.travelbooking.models.entities.travels.TravelEntity;
import md.miller1995.travelbooking.repositories.TravelRepository;
import md.miller1995.travelbooking.services.travels.TravelServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.verify;

class TravelServiceImplTest {

    private final TravelRepository travelRepository = Mockito.mock(TravelRepository.class);
    private final TravelServiceImpl travelServiceImpl = new TravelServiceImpl(travelRepository);

    @Test
    void testShouldCheckIfTravelEntityWasFoundByType(){
        //given
        TravelEntity travelTest = new TravelEntity(
                                                UUID.randomUUID(),
                                            "SKIING",
                                                Date.valueOf(LocalDate.of(2023,3,23)),
                                                Date.valueOf(LocalDate.of(2023,3,26)),
                                        333333.33);
        //when
        travelServiceImpl.findAllTravelByType(travelTest.getType());
        //then
        verify(travelRepository).findTravelEntitiesByType(travelTest.getType());
    }

    @Test
    void testShouldCheckIfTravelEntityWasFoundBetweenDateAndByType(){
        //given
        TravelEntity travelTest = new TravelEntity(
                                                    UUID.randomUUID(),
                                                    "SKIING",
                                                    Date.valueOf(LocalDate.of(2023,3,23)),
                                                    Date.valueOf(LocalDate.of(2023,3,26)),
                                                    333333.33);
        //when
        travelServiceImpl.findAllTravelByDateAndByType("SKIING",
                                                        LocalDate.of(2023,3,23),
                                                        LocalDate.of(2023,3,26));
        //then
        verify(travelRepository)
                .findAllByTypeAndStartDateIsGreaterThanEqualAndEndDateLessThanEqual(
                                                                            travelTest.getType(),
                                                                            travelTest.getStartDate().toLocalDate(),
                                                                            travelTest.getEndDate().toLocalDate());
    }
}
