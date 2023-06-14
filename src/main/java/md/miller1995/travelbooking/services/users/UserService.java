package md.miller1995.travelbooking.services.users;

import md.miller1995.travelbooking.models.dtos.auth.UserRegisterDTO;


public interface UserService {

    /**
     * This method create a query,
     * that save a UserEntity in table "users" in database
     *
     * @author Anton Nirca
     * @since 13/16/2023
     *
     * @param userRegisterDTO
     * @return UserRegisterDTO
     */
    UserRegisterDTO saveUser(UserRegisterDTO userRegisterDTO);

    /**
     * This method create a query,
     * that update a UserEntity in table "users" in database
     *
     * @author Anton Nirca
     * @since 13/16/2023
     *
     * @param userRegisterDTO
     * @return UserRegisterDTO
     */
    UserRegisterDTO updateUserByUsername(String username, UserRegisterDTO userRegisterDTO);

    /**
     *This method create a query,
     *that update user_role a UserEntity in table "users" in database
     *
     *@author Anton Nirca
     * @since 13/16/2023
     *
     * @param username
     * @return UserRegisterDTO
     */

    UserRegisterDTO updateRoleForUserToAdminByUsername(String username);

    /**
     * This method create a query,
     * that delete a UserEntity in table "users" from database
     *
     * @author Anton Nirca
     * @since 13/16/2023
     *
     * @param username
     * @return void
     */
    void deleteUserByUsername(String username);

    /**
     * This method create a query,
     * that find a UserEntity from database after param username
     *
     * @author Anton Nirca
     * @since 13/16/2023
     *
     * @param username
     * @return UserRegisterDTO
     */
    UserRegisterDTO findUserByUsername(String username);
}
