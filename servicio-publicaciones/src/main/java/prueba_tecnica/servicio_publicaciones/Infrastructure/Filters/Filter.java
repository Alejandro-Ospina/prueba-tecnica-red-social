package prueba_tecnica.servicio_publicaciones.Infrastructure.Filters;

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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.MessageDto;
import prueba_tecnica.servicio_publicaciones.Application.Services.JwtService;
import prueba_tecnica.servicio_publicaciones.Application.Validators.JwtValidator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class Filter extends OncePerRequestFilter {

    private final JwtValidator validator;
    private final JwtService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null) {

            String token = header.substring(7);
            String subject = service.getSubjectFromToken(token);
            if (!validator.isTokenValid(token)){
                errorHandler(response, "Session has expired. Try sign in again");
                return;
            }

            if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null){
                Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                                subject,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                        );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
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