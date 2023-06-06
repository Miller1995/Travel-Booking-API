package md.miller1995.travelbooking.integrations.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import md.miller1995.travelbooking.models.dtos.travels.TravelDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@ExtendWith(SpringExtension.class)
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureTestDatabase
class TravelControllerTest {


    private final MockMvc mockMvc;

    @Autowired
    TravelControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Disabled
    @Test
    void testShouldCheckIfTravelWasCreate() throws Exception {
        TravelDTO travelTest = new TravelDTO();
        travelTest.setType("SNOW");
        travelTest.setStartDate(LocalDate.of(2023, 11,11));
        travelTest.setEndDate(LocalDate.of(2023, 11,22));
        travelTest.setAmount(111111.11);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/travels/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonString(travelTest)))
                .andExpect(status().is(200));
    }

    @Disabled
    @Test
    void testShouldCheckIfGetTravelByTypeWasFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/travels")
                .param("type","SNOW")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Disabled
    @Test
    void testShouldCheckIfGetTravelByInvalidTypeWasNotFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/travels")
                .param("type","INVALID")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Disabled
    @Test
    void testShouldCheckIfGetTravelByDateAndByTypeWasFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/travels/search")
                .param("type","SNOW")
                .param("startDate", "2023-11-21")
                .param("endDate", "2023-11-22")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    private String convertObjectToJsonString(Object response){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.findAndRegisterModules();
            String json = objectMapper.writeValueAsString(response);
            if (json.contains("logo")){
                log.info("Resulting JSON string: " + json);
            }
            return json;
        } catch (JsonProcessingException e) {
            log.error("Error processing input ", e.getMessage());
        }
        return null;
    }
}
