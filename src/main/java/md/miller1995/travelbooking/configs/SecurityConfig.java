package md.miller1995.travelbooking.configs;

import md.miller1995.travelbooking.securities.UserAuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserAuthProviderImpl userAuthDetailsProvider;
    private final JWTAuthFilter jwtAuthFilter;

    @Autowired
    public SecurityConfig(UserAuthProviderImpl authProvider, JWTAuthFilter jwtAuthFilter) {
        this.userAuthDetailsProvider = authProvider;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(@NonNull HttpSecurity http) throws Exception {

        http.csrf().disable()
            .authorizeHttpRequests((autz) -> autz
                    .requestMatchers(
                            "/api/v1/auth/**",
                            "/v3/api-docs/**",
                            "/swagger-ui/**" ).permitAll()
                    .requestMatchers("/api/v1/super-admin/**").hasRole("SUPER_ADMIN")
                    .requestMatchers("/api/v1/admin").hasRole("ADMIN")
                    .requestMatchers("/api/v1/admin").hasRole("SUPER_ADMIN")
                    .anyRequest().authenticated());

        http
            .authenticationProvider(userAuthDetailsProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
