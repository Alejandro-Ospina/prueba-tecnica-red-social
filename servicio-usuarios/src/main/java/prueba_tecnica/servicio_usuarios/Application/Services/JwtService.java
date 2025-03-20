package prueba_tecnica.servicio_usuarios.Application.Services;

import prueba_tecnica.servicio_usuarios.Domain.Models.Users;

public interface JwtService {
    String getToken(Users user);
    boolean isTokenValid(String token);
    String getSubject(String token);
}
