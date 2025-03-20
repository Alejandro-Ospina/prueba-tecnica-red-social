package prueba_tecnica.servicio_publicaciones.Application.Dtos;

import java.time.LocalDateTime;

public record MessageDto(
        LocalDateTime timestamp,
        String messages
) {
}
