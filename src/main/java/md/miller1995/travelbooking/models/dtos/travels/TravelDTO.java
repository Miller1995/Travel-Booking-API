package md.miller1995.travelbooking.models.dtos.travels;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelDTO implements Serializable {

    @NotBlank(message = "Type travel can't be empty")
    private String type;
    @NotBlank(message = "Start date can't be empty and format should be yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @NotBlank(message = "Start date can't be empty and format should be yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotBlank(message = "Amount travel can't be empty")
    private Double amount;
}
