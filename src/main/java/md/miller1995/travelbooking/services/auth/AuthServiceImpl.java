package md.miller1995.travelbooking.services.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import md.miller1995.travelbooking.exceptions.users.UserNotFoundException;
import md.miller1995.travelbooking.models.dtos.auth.UserAuthDTO;
import md.miller1995.travelbooking.models.dtos.auth.UserRegisterDTO;
import md.miller1995.travelbooking.models.entities.users.UserEntity;
import md.miller1995.travelbooking.repositories.UserRepository;
import md.miller1995.travelbooking.securities.JWTUtil;
import md.miller1995.travelbooking.utils.AuthResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTUtil jwtUtil, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    @Transactional
    public UserRegisterDTO register(@NonNull UserRegisterDTO userRegisterDTO) {
        var user = UserRegisterDTO.builder()
                .firstName(userRegisterDTO.getFirstName())
                .lastName(userRegisterDTO.getLastName())
                .username(userRegisterDTO.getUsername())
                .email(userRegisterDTO.getEmail())
                .password(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .build();

        UserEntity userAuthEntity = convertUserRegisterDTOToUserEntity(user);
        userAuthEntity.setRole("ROLE_USER");
        UserEntity savedUser = userRepository.save(userAuthEntity);
        UserRegisterDTO returnUser = convertUserEntityToUserRegisterDTO(savedUser);

        return returnUser;
    }

    public AuthResponse authenticate(@NonNull UserAuthDTO userAuthDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDTO.getUsername(),
                        userAuthDTO.getPassword())
        );

        var user = userRepository.findUserEntityByUsername(userAuthDTO.getUsername()).orElseThrow(UserNotFoundException::new);

        String jwtToken = jwtUtil.generateToken(user.getUsername());

        return AuthResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }

    private UserEntity convertUserRegisterDTOToUserEntity(UserRegisterDTO userRegisterDTO) {
        return objectMapper.convertValue(userRegisterDTO, UserEntity.class);
    }

    private UserRegisterDTO convertUserEntityToUserRegisterDTO(UserEntity userEntity) {
        return objectMapper.convertValue(userEntity, UserRegisterDTO.class);
    }
}
