package prueba_tecnica.servicio_publicaciones.Domain.Models;

import jakarta.persistence.*;
import lombok.*;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.CreatePublicationDto;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table (name = "publications")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class Publications {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "creation_date")
    private LocalDateTime creationDate;
    private String topic;
    private String content;
    private Long likes;

    @Column (name = "user_id")
    private Long userId;

    public Publications(CreatePublicationDto dto, Map<String, Object> tokenClaims){
        this.creationDate = LocalDateTime.now();
        this.topic = dto.topic();
        this.content = dto.content();
        Object idValue = tokenClaims.get("id");
        this.userId = (idValue instanceof Number) ? ((Number) idValue).longValue() : null;
    }
}
