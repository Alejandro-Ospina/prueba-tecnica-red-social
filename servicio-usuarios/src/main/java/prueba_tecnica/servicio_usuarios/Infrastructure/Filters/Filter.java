package prueba_tecnica.servicio_usuarios.Infrastructure.Filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import prueba_tecnica.servicio_usuarios.Application.Dtos.MessageDto;
import prueba_tecnica.servicio_usuarios.Application.Services.JwtService;
import prueba_tecnica.servicio_usuarios.Domain.Repositories.UsersRepository;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsersRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header != null) {

            String token = header.substring(7);
            if (jwtService.isTokenValid(token)){

                var userAuthenticated = repository.findByEmail(jwtService.getSubject(token)).orElse(null);
                if (userAuthenticated != null && !userAuthenticated.getActive()){
                    errorHandler(response, "User not active. Permission denied!");
                    return;
                }

                if (userAuthenticated != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    Authentication autenticacion = new UsernamePasswordAuthenticationToken(
                            userAuthenticated,
                            null,
                            userAuthenticated.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(autenticacion);
                }else{
                    errorHandler(response, "User not found. Sign in again!");
                    return;
                }
            }else{
                errorHandler(response, "Access token not valid. Sign in again.");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void errorHandler(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(
                new ObjectMapper()
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
