package prueba_tecnica.servicio_publicaciones.Application.Dtos;

import prueba_tecnica.servicio_publicaciones.Domain.Models.Publications;

import java.time.LocalDateTime;
import java.util.Map;

public record ShowPublicationDto(
        Long id,
        LocalDateTime date,
        String topic,
        String content,
        Long likes,
        Long authorId
) {
    public ShowPublicationDto(Publications publication){
        this(
                publication.getId(),
                publication.getCreationDate(),
                publication.getTopic(),
                publication.getContent(),
                publication.getLikes(),
                publication.getUserId()
        );
    }
}
