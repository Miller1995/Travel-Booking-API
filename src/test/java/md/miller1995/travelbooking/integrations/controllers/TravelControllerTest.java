package md.miller1995.travelbooking.integrations.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import md.miller1995.travelbooking.models.dtos.auth.UserRegisterDTO;
import md.miller1995.travelbooking.models.dtos.travels.TravelDTO;
import md.miller1995.travelbooking.securities.JWTUtil;
import md.miller1995.travelbooking.services.users.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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


        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                .post("/api/v1/travels/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonString(travelTest)))
                .andExpect(status().is(200))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
    }

    @Disabled
    @Test
    void testShouldCheckIfTravelWasNotCreate() throws Exception {
        TravelDTO travelTest = new TravelDTO();
        travelTest.setType("SNOW");
        travelTest.setStartDate(LocalDate.of(2023, 11,11));
        travelTest.setEndDate(LocalDate.of(2023, 11,22));
        travelTest.setAmount(111111.11);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/travels/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonString(travelTest)))
                .andExpect(status().is(403))
                .andReturn();

        assertEquals(403, result.getResponse().getStatus());
    }

    @Disabled
    @Test
    void testShouldCheckIfGetTravelByTypeWasFound() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/travels")
                .param("type","SNOW")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
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
