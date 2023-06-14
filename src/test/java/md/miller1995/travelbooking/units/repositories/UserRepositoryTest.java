package md.miller1995.travelbooking.units.repositories;

import md.miller1995.travelbooking.models.entities.users.UserEntity;
import md.miller1995.travelbooking.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private UserEntity userEntityTest;

    @BeforeEach
    void setUp(){
        // given
        userEntityTest = new UserEntity(
                UUID.randomUUID(),
                "John",
                "Doe",
                "Killer",
                "joe.doe@gmail.com",
                "password",
                "ROLE_USER"
        );

        userRepository.save(userEntityTest);
    }

    @AfterEach
    void tearDown(){
        userEntityTest = null;
        userRepository.deleteAll();
    }

    @Test
    void testShouldCheckIfUserEntityWasFoundByUsername(){
        //when
        Optional userEntity = userRepository.findUserEntityByUsername("Killer");
        //then
        assertThat(userEntity.equals(userEntityTest));
    }

    @Test
    void testShouldCheckIfUserEntityWasNotFoundByUsername(){
        //when
        Optional userEntity = userRepository.findUserEntityByUsername("NoName");
        //then
        assertThat(userEntity.isEmpty());
    }

    @Test
    void testShouldCheckIfUserEntityWasDeleteByUsername(){
        //when
        userRepository.deleteByUsername("Killer");
        //then
        assertThat(userEntityTest.equals(null));
    }

    @Test
    void testShouldCheckIfUserEntityWasNotDeleteByUsername(){
        //when
        userRepository.deleteByUsername("NoName");
        //then
        assertThat(userEntityTest.getUsername().compareTo("Killer"));
    }
}
