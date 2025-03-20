package prueba_tecnica.servicio_publicaciones.Infrastructure.Implementations.ServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.CreatePublicationDto;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.ShowPublicationDto;
import prueba_tecnica.servicio_publicaciones.Application.Services.JwtService;
import prueba_tecnica.servicio_publicaciones.Application.Services.PublicationsService;
import prueba_tecnica.servicio_publicaciones.Domain.Models.Publications;
import prueba_tecnica.servicio_publicaciones.Domain.Repositories.PublicationsRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PublicationsServiceImpl implements PublicationsService {

    private final JwtService jwtService;
    private final PublicationsRepository repository;

    @Override
    public ShowPublicationDto createPublication(CreatePublicationDto dto, String token) {
        var claims = jwtService.getClaimsFromToken(token);
        var publication = new Publications(dto, claims);
        repository.save(publication);
        return new ShowPublicationDto(publication);
    }

    @Override
    @Transactional
    public void updatePublication(Map<String, Object> publicationDetails, String token) {
        if (!publicationDetails.containsKey("id"))
            throw new RuntimeException("Updating publications require you to specify an id.");
        Object idValue = publicationDetails.get("id");
        Long id = (idValue instanceof Number) ? ((Number) idValue).longValue() : null;
        var publication = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("There is no coincidence with publication id " + id)
        );
        Object idAuthor = jwtService.getClaimsFromToken(token).get("id");
        Long authorId = (idAuthor instanceof Number) ? ((Number) idAuthor).longValue() : null;
        if (!Objects.equals(authorId, publication.getUserId()))
            throw new RuntimeException("You do not have permission to edit the publication.");

        publication.setTopic((String) publicationDetails.get("topic"));
        publication.setContent((String) publicationDetails.get("content"));
        publication.setCreationDate(LocalDateTime.now());
    }

    @Override
    public void deletePublication(Long idPublication, String token) {
        var publication = repository.findById(idPublication).orElseThrow(
                () -> new EntityNotFoundException("There is no such publication with id " + idPublication)
        );

        Object idAuthor = jwtService.getClaimsFromToken(token).get("id");
        Long authorId = (idAuthor instanceof Number) ? ((Number) idAuthor).longValue() : null;
        if (!Objects.equals(authorId, publication.getUserId()))
            throw new RuntimeException("You do not have permission to delete the publication.");

        repository.delete(publication);
    }

    @Override
    @Transactional
    public void giveLike(Long idPublication) {
        var publication = repository.findById(idPublication).orElseThrow(
                () -> new EntityNotFoundException("There is no such publication with id " + idPublication)
        );
        if (publication.getLikes() == null) publication.setLikes(0L);
        publication.setLikes(publication.getLikes() + 1);
    }

    @Override
    public Page<ShowPublicationDto> getPublicationList(Pageable pageable, String token) {
        Object idAuthor = jwtService.getClaimsFromToken(token).get("id");
        Long authorId = (idAuthor instanceof Number) ? ((Number) idAuthor).longValue() : null;
        return repository.findAllByUserId(authorId, pageable).map(ShowPublicationDto::new);
    }

    @Override
    public ShowPublicationDto getPublicationDetail(Long id) {
        var publication = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("There is no such publication with id " + id)
        );

        return new ShowPublicationDto(publication);
    }

    @Override
    public Page<ShowPublicationDto> getAllPublications(Pageable pagebale) {
        return repository.findAll(pagebale).map(ShowPublicationDto::new);
    }
}
