package md.miller1995.travelbooking.units.repositories;

import md.miller1995.travelbooking.models.entities.travels.TravelEntity;
import md.miller1995.travelbooking.repositories.TravelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TravelRepositoryTest {

    @Autowired
    private TravelRepository travelRepository;
    private TravelEntity travelEntityTest;

    @BeforeEach
    void setUp(){
        // given
        travelEntityTest = new TravelEntity(
                                            UUID.randomUUID(),
                                            "SKIING",
                                            Date.valueOf(LocalDate.of(2023,11,21)),
                                            Date.valueOf(LocalDate.of(2023,11,25)),
                                            333333.33);

        travelRepository.save(travelEntityTest);
    }

    @AfterEach
    void tearDown(){
        travelEntityTest = null;
        travelRepository.deleteAll();
    }

    @Test
    void testShouldCheckIfTravelEntityWasFoundByType(){
        //when
        List<TravelEntity> travelEntityList = travelRepository.findTravelEntitiesByType("SKIING");
        //then
        assertThat(travelEntityList.get(0).getStartDate().compareTo(travelEntityTest.getStartDate()));
        assertThat((travelEntityList.get(0).getAmount()).compareTo(travelEntityTest.getAmount()));
    }

    @Test
    void testShouldCheckIfTravelEntityWasNotFoundByType(){
        //when
        List<TravelEntity> travelEntityList = travelRepository.findTravelEntitiesByType(travelEntityTest.getType());
        //then
        assertThat(travelEntityList.get(0).getId()).isNotEqualByComparingTo(travelEntityTest.getId());
        assertThat(travelEntityList.get(0).getAmount()).isNotEqualByComparingTo(22222.22);
    }

    @Test
    void testShouldCheckIfTravelEntityWasFoundBetweenDatesAndByType(){
        //when
        List<TravelEntity> travelEntityList = travelRepository
                .findAllByTypeAndStartDateIsGreaterThanEqualAndEndDateLessThanEqual("SKIING",
                                                                                    LocalDate.of(2023,11,21),
                                                                                    LocalDate.of(2023,11,29));
        //then

        assertThat(travelEntityList.get(0).getType().compareTo(travelEntityTest.getType()));
        assertThat((travelEntityList.get(0).getAmount()).compareTo(travelEntityTest.getAmount()));
    }

    @Test
    void testShouldCheckIfTravelEntityWasNotFoundBetweenDatesAndByType(){
        //when
        List<TravelEntity> travelEntityList = travelRepository
                .findAllByTypeAndStartDateIsGreaterThanEqualAndEndDateLessThanEqual("SKIING",
                                                                                    LocalDate.of(2023,11,21),
                                                                                    LocalDate.of(2023,11,29));
        //then

        assertThat(travelEntityList.get(0).getType().compareTo("travelEntityTest.getType()"));
        assertThat(travelEntityList.get(0).getAmount()).isNotEqualByComparingTo(3334.44);
    }
}
