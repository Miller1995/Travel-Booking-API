package md.miller1995.travelbooking.securities;

import md.miller1995.travelbooking.services.auth.UserAuthDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthProviderImpl implements AuthenticationProvider {

    private final UserAuthDetailsServiceImpl userAuthDetailsServiceImpl;
    private  final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthProviderImpl(UserAuthDetailsServiceImpl userAuthDetailsServiceImpl, PasswordEncoder passwordEncoder) {
        this.userAuthDetailsServiceImpl = userAuthDetailsServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // logic for authentication
        String username = authentication.getName();
        UserDetails userDetails = userAuthDetailsServiceImpl.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, authentication.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}