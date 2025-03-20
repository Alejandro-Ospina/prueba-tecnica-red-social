package prueba_tecnica.servicio_usuarios.Infrastructure.Implementations;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prueba_tecnica.servicio_usuarios.Application.Services.UsersDetailService;
import prueba_tecnica.servicio_usuarios.Domain.Repositories.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersDetailServiceImpl implements UsersDetailService {

    private final UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User not found.")
        );
    }
}
