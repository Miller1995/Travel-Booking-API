package md.miller1995.travelbooking.services.auth;

import md.miller1995.travelbooking.models.dtos.auth.UserAuthDTO;
import md.miller1995.travelbooking.models.dtos.auth.UserRegisterDTO;
import md.miller1995.travelbooking.utils.AuthResponse;

public interface AuthService {

    /**
     * This method is used to register a new user in the database
     *
     * @author Anton Nirca
     * @since  23/05/2023
     *
     * @param userRegisterDTO
     */
    UserRegisterDTO register(UserRegisterDTO userRegisterDTO);

    /**
     * This method is used for authenticate a user and return the jwt-token for this user
     *
     * @param userAuthDTO
     * @author Anton Nirca
     * @since 23/05/2023
     */
    AuthResponse authenticate(UserAuthDTO userAuthDTO);
}
