package cl.duoc.banco_bff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Endpoint de salud
                .requestMatchers("/actuator/health").permitAll()

                // Operaciones de cliente
                .requestMatchers(HttpMethod.POST, "/api/solicitudes")
                    .hasRole("Cliente")

                .requestMatchers(HttpMethod.GET, "/api/solicitudes/**")
                    .hasAnyRole("Cliente", "Empleado")

                .requestMatchers(HttpMethod.GET, "/api/cuentas/**")
                    .hasAnyRole("Cliente", "Empleado")

                // Operaciones administrativas
                .requestMatchers(HttpMethod.PUT, "/api/solicitudes/*/aprobar")
                    .hasRole("Empleado")

                .requestMatchers(HttpMethod.PUT, "/api/solicitudes/*/rechazar")
                    .hasRole("Empleado")

                // Cualquier otra petición necesita autenticación
                .anyRequest().authenticated()
            )

            .oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt ->
                    jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                )
            );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter authoritiesConverter =
                new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName("cognito:groups");
        authoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }
}
