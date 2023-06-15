package md.miller1995.travelbooking.exceptions.reviews;

import md.miller1995.travelbooking.exceptions.users.UserNotFoundException;
import md.miller1995.travelbooking.utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReviewExceptionHandlerAdvice {

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReviewNotFoundException(ReviewNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(
                "Review wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
