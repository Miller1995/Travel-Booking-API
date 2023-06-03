package md.miller1995.travelbooking.controllers;

import io.swagger.v3.oas.annotations.Operation;
import md.miller1995.travelbooking.models.dtos.travels.TravelDTO;
import md.miller1995.travelbooking.services.travels.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/travels")
public class TravelController {

    private final TravelService travelService;

    @Autowired
    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @PostMapping("/add")
    @Operation(summary = "Route to save travel in database",
                description = "Return single result, the travel which was saved" )
    public ResponseEntity<TravelDTO> saveTravel(@RequestBody TravelDTO travelDTO){
        return ResponseEntity.ok(travelService.saveTravel(travelDTO));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Route to update travel in database",
                description = "Return single result, the travel which was updated")
    public ResponseEntity<TravelDTO> updateTravel(@RequestBody TravelDTO travelDTO,
                                                  @PathVariable("id") UUID uuid){
        return ResponseEntity.ok(travelService.updateTravel(uuid, travelDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Route to delete travel from database",
                description = "Return Http Status")
    public ResponseEntity<HttpStatus> deleteTravel (@PathVariable("id") UUID id){
        travelService.deleteTravel(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    @Operation(summary = "Route to find all travels by them type",
                description = "Travel search by %type% format and return list of travels from database")
    public ResponseEntity<List<TravelDTO>> getTravelByType(@RequestParam(value = "type") String type){
        List<TravelDTO> travelDTOList = travelService.findAllTravelByType(type);
        return ResponseEntity.ok(travelDTOList);
    }

    @GetMapping("/search")
    @Operation(summary = "Route to find all travels between two dates and type",
                description = "Travel search by %type%, %startDate%, %andDate% and return list of travels from database")
    public ResponseEntity<List<TravelDTO>> getTravelByDateAndByType(@RequestParam(value = "type") String type,
                                                                    @RequestParam(value = "startDate")LocalDate startDate,
                                                                    @RequestParam(value = "endDate") LocalDate endDate){
        return ResponseEntity.ok(travelService.findAllTravelByDateAndByType(type, startDate, endDate));
    }


}
