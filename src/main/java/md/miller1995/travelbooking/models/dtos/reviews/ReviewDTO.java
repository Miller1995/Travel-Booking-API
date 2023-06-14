package md.miller1995.travelbooking.models.dtos.reviews;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO implements Serializable {

    private String review;
    private String place;
    private double rating;

}
