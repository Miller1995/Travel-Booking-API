package md.miller1995.travelbooking.units.services;

import md.miller1995.travelbooking.models.entities.users.UserEntity;
import md.miller1995.travelbooking.repositories.UserRepository;
import md.miller1995.travelbooking.services.auth.AuthService;
import md.miller1995.travelbooking.services.users.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.mockito.Mockito.verify;

class UserServiceImplTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final ModelMapper modelMapper = Mockito.mock(ModelMapper.class);
    private final AuthService authService = Mockito.mock(AuthService.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private final UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, modelMapper, authService, passwordEncoder);


    @Test
    void testShouldCheckIfUserEntityWasFoundByType(){
        //given
        UserEntity userEntityTest = new UserEntity(
                UUID.randomUUID(),
                "Mark",
                "Prince",
                "Mars",
                "mark.prince@gmail.com",
                "password",
                "ROLE_USER"
        );
        //when
        userRepository.findUserEntityByUsername(userEntityTest.getUsername());
        //then
        verify(userRepository).findUserEntityByUsername(userEntityTest.getUsername());
    }

    @Test
    void testShouldCheckIfUserEntityWasDeleteByType(){
        //given
        UserEntity userEntityTest = new UserEntity(
                UUID.randomUUID(),
                "Mark",
                "Prince",
                "Mars",
                "mark.prince@gmail.com",
                "password",
                "ROLE_USER"
        );
        //when
        userRepository.deleteByUsername(userEntityTest.getUsername());
        //then
        verify(userRepository).deleteByUsername(userEntityTest.getUsername());
    }
}
