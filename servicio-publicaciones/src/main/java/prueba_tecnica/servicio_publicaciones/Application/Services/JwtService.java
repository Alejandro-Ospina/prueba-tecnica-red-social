package prueba_tecnica.servicio_publicaciones.Application.Services;

import java.util.Map;

public interface JwtService {

    Map<String, Object> getClaimsFromToken(String token);
    String getSubjectFromToken(String token);
}
