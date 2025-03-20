package prueba_tecnica.servicio_publicaciones.Infrastructure.Implementations.ServiceImpl;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import prueba_tecnica.servicio_publicaciones.Application.Services.JwtService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class JwtServiceImpl implements JwtService {

    @Value("${token.signature}")
    private String tokenSignature;

    @Override
    public Map<String, Object> getClaimsFromToken(String token) {
        try{
            return Jwts.parser()
                    .verifyWith(getKeySigned())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch(JwtException | IllegalArgumentException ex){
            throw new RuntimeException("User has not been verified.");
        }
    }

    @Override
    public String getSubjectFromToken(String token) {
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

    private SecretKey getKeySigned(){
        return Keys.hmacShaKeyFor(tokenSignature.getBytes(StandardCharsets.UTF_8));
    }
}
