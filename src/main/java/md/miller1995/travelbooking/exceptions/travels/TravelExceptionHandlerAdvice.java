package md.miller1995.travelbooking.exceptions.travels;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TravelExceptionHandlerAdvice {

    @ExceptionHandler(TravelNotFoundException.class)
    private ResponseEntity<Object> handleTravelNotFoundException(TravelNotFoundException exception){
        Map<String, Object> response = new HashMap<>();
        response.put("Travel with this id was not found ", exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
