package md.miller1995.travelbooking.controllers;

import io.swagger.v3.oas.annotations.Operation;
import md.miller1995.travelbooking.models.dtos.auth.UserAuthDTO;
import md.miller1995.travelbooking.models.dtos.auth.UserRegisterDTO;
import md.miller1995.travelbooking.services.auth.AuthService;
import md.miller1995.travelbooking.utils.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    @Operation(summary = "this endpoint is used to register a new user in database")
    public ResponseEntity<UserRegisterDTO> performRegistration(@RequestBody UserRegisterDTO userRegisterDTO){
        authService.register(userRegisterDTO);
        return ResponseEntity.ok(userRegisterDTO);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "this endpoint is used to login the user in app and return the jwt token for this user")
    public ResponseEntity<AuthResponse> performAuthenticate(@RequestBody UserAuthDTO userAuthDTO){
        AuthResponse authResponse = authService.authenticate(userAuthDTO);
        return ResponseEntity.ok(authResponse);
    }
}
