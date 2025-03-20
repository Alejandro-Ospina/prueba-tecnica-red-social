package prueba_tecnica.servicio_publicaciones.Infrastructure.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.MessageDto;
import prueba_tecnica.servicio_publicaciones.Infrastructure.Filters.Filter;

import java.io.IOException;
import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final Filter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/servicio-publicaciones.html",
                                        "/v1/servicio-publicaciones/**",
                                        "/swagger-ui/**",
                                        "/api/docs/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/publications/detail/{id}").permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(error ->
                        error.authenticationEntryPoint((request,
                                                        response,
                                                        authException) -> {
                            answer(response, "Access denied. Try sign in again.");
                        }))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private void answer(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper()
                .registerModules(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .writeValueAsString(
                        new MessageDto(
                                LocalDateTime.now(),
                                message
                        )
                )
        );
    }
}
