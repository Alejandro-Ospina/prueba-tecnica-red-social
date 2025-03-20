package prueba_tecnica.servicio_publicaciones.Application.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.CreatePublicationDto;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.ShowPublicationDto;

import java.util.Map;

public interface PublicationsService {

    ShowPublicationDto createPublication (CreatePublicationDto dto, String token);
    void updatePublication(Map<String, Object> publicationDetails, String token);
    void deletePublication(Long idPublication, String token);
    void giveLike(Long idPublication);
    ShowPublicationDto getPublicationDetail(Long id);
    Page<ShowPublicationDto> getPublicationList(Pageable pageable, String token);
    Page<ShowPublicationDto> getAllPublications(Pageable pagebale);
}
