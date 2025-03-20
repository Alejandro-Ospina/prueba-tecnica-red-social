package prueba_tecnica.servicio_usuarios.Infrastructure.Implementations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import prueba_tecnica.servicio_usuarios.Application.Services.JwtService;
import prueba_tecnica.servicio_usuarios.Domain.Models.Users;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${token.signature}")
    private String tokenSignature;

    @Value("${token.expiration}")
    private int tokenExpiration;

    @Override
    public String getToken(Users user) {
        return Jwts.builder()
                .header()
                .type("ACCESS")
                .and()
                .issuer("Periferia")
                .subject(user.getEmail())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("surname", user.getSurname())
                .claim("active", user.getActive())
                .issuedAt(Date.from(Instant.now()))
                .expiration(tokenExpiration(tokenExpiration))
                .signWith(getKeySigned(), Jwts.SIG.HS512)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        Claims claims;
        try{
            claims = Jwts.parser()
                    .verifyWith(getKeySigned())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            boolean userActive = (boolean) claims.get("active");
            return claims.getExpiration().after(new Date()) && userActive;
        } catch(JwtException | IllegalArgumentException ex){
            return false;
        }
    }

    @Override
    public String getSubject(String token) {
        try{
            return Jwts.parser()
                    .verifyWith(getKeySigned())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch(JwtException | IllegalArgumentException ex){
            throw new RuntimeException("User has not been verified.");
        }
    }

    private Date tokenExpiration(int seconds){
        return Date.from(Instant.now().plusSeconds(seconds));
    }

    private SecretKey getKeySigned(){
        return Keys.hmacShaKeyFor(tokenSignature.getBytes(StandardCharsets.UTF_8));
    }
}
