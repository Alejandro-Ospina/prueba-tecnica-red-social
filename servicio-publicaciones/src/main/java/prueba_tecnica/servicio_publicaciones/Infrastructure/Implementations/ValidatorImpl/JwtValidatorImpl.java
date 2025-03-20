package prueba_tecnica.servicio_publicaciones.Infrastructure.Implementations.ValidatorImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import prueba_tecnica.servicio_publicaciones.Application.Validators.JwtValidator;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtValidatorImpl implements JwtValidator {

    @Value("${token.signature}")
    private String tokenSignature;

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

    private SecretKey getKeySigned(){
        return Keys.hmacShaKeyFor(tokenSignature.getBytes(StandardCharsets.UTF_8));
    }
}
