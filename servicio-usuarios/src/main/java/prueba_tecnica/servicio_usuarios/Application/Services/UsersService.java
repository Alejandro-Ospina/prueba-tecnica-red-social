package prueba_tecnica.servicio_usuarios.Application.Services;

import org.springframework.security.core.Authentication;
import prueba_tecnica.servicio_usuarios.Application.Dtos.CreatedUserDto;
import prueba_tecnica.servicio_usuarios.Application.Dtos.UserDetailsDto;

import java.util.Map;

public interface UsersService {

    CreatedUserDto createUser(UserDetailsDto dto);
    void updateUser(Map<String, Object> detail, Authentication authentication);
    void deleteUser(Authentication authentication);
    CreatedUserDto getUserCreatedDetails(Long id);
}
