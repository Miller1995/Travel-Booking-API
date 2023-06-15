package md.miller1995.travelbooking.controllers;

import io.swagger.v3.oas.annotations.Operation;
import md.miller1995.travelbooking.models.dtos.reviews.ReviewDTO;
import md.miller1995.travelbooking.services.reviews.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    @Operation(summary = "Route to add review in database",
            description = "Return single result, the review which was saved" )
    public ResponseEntity<ReviewDTO> addReview (@RequestBody ReviewDTO reviewDTO){
        return ResponseEntity.ok(reviewService.addReview(reviewDTO));
    }

    @GetMapping("/{place}")
    @Operation(summary = "Route to find all reviews by place",
            description = "Return list of reviews from database")
    public ResponseEntity<List<ReviewDTO>> getAllReviewByPlace(@PathVariable("place") String place){
        return ResponseEntity.ok(reviewService.getAllReviewsByPlace(place));
    }
}
