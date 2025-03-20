package prueba_tecnica.servicio_publicaciones.Application.Dtos;

import jakarta.validation.constraints.NotEmpty;

public record CreatePublicationDto(
        @NotEmpty
        String topic,

        @NotEmpty
        String content
) {
}
