package md.miller1995.travelbooking.services.auth;

import md.miller1995.travelbooking.models.entities.UserEntity;
import md.miller1995.travelbooking.repositories.UserRepository;
import md.miller1995.travelbooking.securities.UserAuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findUserEntityByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        return new UserAuthDetails(user.get());
    }
}
