package md.miller1995.travelbooking.models.entities.reviews;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import md.miller1995.travelbooking.models.entities.users.UserEntity;

import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "reviews")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "review")
    private String review;
    @Column(name = "place")
    private String place;
    @Column(name = "rating")
    private double rating;
    @Column(name = "created_at")
    private Date createdAt;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserEntity owner;
}
