package md.miller1995.travelbooking.controllers;

import io.swagger.v3.oas.annotations.Operation;
import md.miller1995.travelbooking.models.dtos.auth.UserRegisterDTO;
import md.miller1995.travelbooking.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @Operation(summary = "Route to save user in database",
            description = "Return single result, the user which was saved" )
    public ResponseEntity<UserRegisterDTO> saveUser (@RequestBody UserRegisterDTO userRegisterDTO){
        return ResponseEntity.ok(userService.saveUser(userRegisterDTO));
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Route to delete user from database after username",
            description = "Return Http Status")
    public ResponseEntity<HttpStatus> deleteUserByUsername(@PathVariable("username") String username){
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{username}")
    @Operation(summary = "Route to update user_role for userEntity in database after username",
            description = "Return single result, the user which was updated")
    public ResponseEntity<UserRegisterDTO> updateRoleToAdminByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.updateRoleForUserToAdminByUsername(username));
    }

    @PostMapping("/update/{username}")
    @Operation(summary = "Route to update userEntity in database",
            description = "Return single result, the user which was updated")
    public ResponseEntity<UserRegisterDTO> updateUserByUsername(@PathVariable("username") String username,
                                                                @RequestBody UserRegisterDTO userRegisterDTO){
        return ResponseEntity.ok(userService.updateUserByUsername(username, userRegisterDTO));
    }

    @GetMapping()
    @Operation(summary = "Route to find  userEntity in database after username",
            description = "Return single result, the user which was found")
    public  ResponseEntity<UserRegisterDTO> findUserByUsername(@RequestParam(value = "username") String username){
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }
}
