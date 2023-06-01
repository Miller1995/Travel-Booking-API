package md.miller1995.travelbooking.services.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import md.miller1995.travelbooking.models.dtos.auth.UserAuthDTO;
import md.miller1995.travelbooking.models.dtos.auth.UserRegisterDTO;
import md.miller1995.travelbooking.models.entities.UserEntity;
import md.miller1995.travelbooking.repositories.UserRepository;
import md.miller1995.travelbooking.securities.JWTUtil;
import md.miller1995.travelbooking.utils.AuthResponse;
import md.miller1995.travelbooking.utils.UserRole;
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

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public void register(@NonNull UserRegisterDTO userRegisterDTO) {
        var user = UserRegisterDTO.builder()
                .firstName(userRegisterDTO.getFirstName())
                .lastName(userRegisterDTO.getLastName())
                .username(userRegisterDTO.getUsername())
                .email(userRegisterDTO.getEmail())
                .password(passwordEncoder.encode(userRegisterDTO.getPassword()))
                .build();

        UserEntity userAuthEntity = convertUserRegisterDTOToUserEntity(user);
        userAuthEntity.setRole(UserRole.USER);
        userRepository.save(userAuthEntity);
    }

    public AuthResponse authenticate(@NonNull UserAuthDTO userAuthDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthDTO.getUsername(),
                        userAuthDTO.getPassword())
        );

        var user = userRepository.findUserEntityByUsername(userAuthDTO.getUsername()).orElseThrow();

        String jwtToken = jwtUtil.generateToken(user.getUsername());

        return AuthResponse.builder()
                .jwtToken(jwtToken)
                .build();
    }

    private UserEntity convertUserRegisterDTOToUserEntity(UserRegisterDTO userRegisterDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.convertValue(userRegisterDTO, UserEntity.class);
    }
}
