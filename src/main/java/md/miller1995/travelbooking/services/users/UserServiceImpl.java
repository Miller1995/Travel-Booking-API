package md.miller1995.travelbooking.services.users;

import md.miller1995.travelbooking.exceptions.users.UserNotFoundException;
import md.miller1995.travelbooking.models.dtos.auth.UserRegisterDTO;
import md.miller1995.travelbooking.models.entities.users.UserEntity;
import md.miller1995.travelbooking.repositories.UserRepository;
import md.miller1995.travelbooking.services.auth.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthService authService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SUPER_ADMIN')")
    public UserRegisterDTO saveUser(UserRegisterDTO userRegisterDTO) {
        return authService.register(userRegisterDTO);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SUPER_ADMIN')")
    public UserRegisterDTO updateUserByUsername(String username, UserRegisterDTO userRegisterDTO) {
        var userToBeUpdate = userRepository.findUserEntityByUsername(username).get();

        userToBeUpdate.setFirstName(userRegisterDTO.getFirstName());
        userToBeUpdate.setLastName(userRegisterDTO.getLastName());
        userToBeUpdate.setUsername(userRegisterDTO.getUsername());
        userToBeUpdate.setEmail(userRegisterDTO.getEmail());
        userToBeUpdate.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        UserEntity updatedUser = userRepository.save(userToBeUpdate);
        UserRegisterDTO returnUser = convertUserEntityToUserRegisterDTO(updatedUser);
        return returnUser;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public UserRegisterDTO updateRoleForUserToAdminByUsername(String username){
        var userToBeUpdate = userRepository.findUserEntityByUsername(username).get();
        userToBeUpdate.setRole("ROLE_ADMIN");

        UserEntity updatedUser = userRepository.save(userToBeUpdate);
        UserRegisterDTO returnUser = convertUserEntityToUserRegisterDTO(updatedUser);
        return returnUser;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SUPER_ADMIN')")
    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('SUPER_ADMIN')")
    public UserRegisterDTO findUserByUsername(String username) {
        var userFound = userRepository.findUserEntityByUsername(username).orElseThrow(UserNotFoundException::new);
        UserRegisterDTO userRegisterDTO = convertUserEntityToUserRegisterDTO(userFound);
        return userRegisterDTO;
    }

    private UserEntity convertUserRegisterDTOToUserEntity(UserRegisterDTO userRegisterDTO) {
        return modelMapper.map(userRegisterDTO, UserEntity.class);
    }

    private UserRegisterDTO convertUserEntityToUserRegisterDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserRegisterDTO.class);
    }
}
