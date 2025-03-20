package prueba_tecnica.servicio_publicaciones.Domain.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prueba_tecnica.servicio_publicaciones.Domain.Models.Publications;

@Repository
public interface PublicationsRepository extends JpaRepository<Publications, Long> {

    Page<Publications> findAllByUserId(Long userId, Pageable pageable);
}
