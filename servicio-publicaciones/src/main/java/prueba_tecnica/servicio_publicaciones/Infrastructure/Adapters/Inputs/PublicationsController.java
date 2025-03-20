package prueba_tecnica.servicio_publicaciones.Infrastructure.Adapters.Inputs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.CreatePublicationDto;
import prueba_tecnica.servicio_publicaciones.Application.Services.PublicationsService;

import java.net.URI;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/publications")
public class PublicationsController {

    private final PublicationsService service;

    @PostMapping ("/create")
    public ResponseEntity<?> createPublication(@RequestBody @Valid CreatePublicationDto dto,
                                               @RequestHeader("Authorization") String bearerToken,
                                               UriComponentsBuilder builder){
        String token = bearerToken.substring(7);
        var publicationDto = service.createPublication(dto, token);

        URI location = builder
                .path("/publications/detail/{id}")
                .buildAndExpand(publicationDto.id())
                .toUri();

        return ResponseEntity.ok().location(location).build();
    }

    @PatchMapping ("/update")
    public ResponseEntity<?> updatePublication(@RequestBody @Valid Map<String, Object> details,
                                               @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        service.updatePublication(details, token);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deletePublication(@PathVariable @Positive Long id,
                                               @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        service.deletePublication(id, token);
        return ResponseEntity.accepted().body("Publication deleted Successfully");
    }

    @GetMapping ("/list")
    public ResponseEntity<?> getPublicationList(@PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC)
                                                Pageable pageable,
                                                @RequestHeader("Authorization") String bearerToken){
        String token = bearerToken.substring(7);
        return ResponseEntity.ok().body(
                service.getPublicationList(pageable, token)
        );
    }

    @PatchMapping ("/like/{id}")
    public ResponseEntity<?> giveLike(@PathVariable @Positive Long id){
        service.giveLike(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping ("/detail/{id}")
    public ResponseEntity<?> showPublicationDetail(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(service.getPublicationDetail(id));
    }

    @GetMapping
    public ResponseEntity<?> showAllPublications(@PageableDefault(size = 5, sort = "creationDate", direction = Sort.Direction.DESC)
                                                 Pageable pageable){
        return ResponseEntity.ok().body(service.getAllPublications(pageable));
    }
}
