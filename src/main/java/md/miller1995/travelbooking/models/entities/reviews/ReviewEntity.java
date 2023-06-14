package md.miller1995.travelbooking.models.entities.reviews;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.miller1995.travelbooking.models.entities.users.UserEntity;

import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "reviews")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity owner;
}
