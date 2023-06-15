package md.miller1995.travelbooking.models.entities.users;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import md.miller1995.travelbooking.models.entities.reviews.ReviewEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    @JsonManagedReference
    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReviewEntity> reviews = new ArrayList<>();

    public UserEntity(UUID id, String firstName, String lastName, String username, String email, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
