package prueba_tecnica.servicio_usuarios.Application.Dtos;

import java.time.LocalDateTime;

public record MessageDto(
        LocalDateTime timestamp,
        String message
) {
}
