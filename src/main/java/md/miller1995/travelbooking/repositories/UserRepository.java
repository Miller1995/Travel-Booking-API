package md.miller1995.travelbooking.repositories;

import md.miller1995.travelbooking.models.entities.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    /**
     * This method create a custom query,
     * that find/select a username of UserEntity from table "users" after parameter/column "username"
     *
     * @author Anton Nirca
     * @since 28/05/2023
     *
     * @param username
     * @return UserAuthEntity
     */
    Optional<UserEntity> findUserEntityByUsername(String username);

    /**
     * This method create a query,
     * that delete a  UserEntity from table "users" after parameter/column "username"
     *
     * @author Anton Nirca
     * @since 13/06/2023
     *
     * @param username
     * @return void
     */
    void deleteByUsername(String username);
}
