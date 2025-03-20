package prueba_tecnica.servicio_usuarios.Infrastructure.Implementations;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import prueba_tecnica.servicio_usuarios.Application.Dtos.CreatedUserDto;
import prueba_tecnica.servicio_usuarios.Application.Dtos.UserDetailsDto;
import prueba_tecnica.servicio_usuarios.Application.Services.UsersService;
import prueba_tecnica.servicio_usuarios.Domain.Models.Users;
import prueba_tecnica.servicio_usuarios.Domain.Repositories.UsersRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public CreatedUserDto createUser(UserDetailsDto dto) {
        repository.findByEmail(dto.email()).ifPresent(
                error -> {
                    throw new EntityExistsException("User already exists. Try another user.");
                }
        );

        var user = new Users(dto);
        user.setPass(encoder.encode(dto.pass()));
        repository.save(user);

        return new CreatedUserDto(user);
    }

    @Override
    @Transactional
    public void updateUser(Map<String, Object> detail, Authentication authentication) {
        if (!detail.containsKey("id"))
            throw new RuntimeException("Updating user require you to specify an id.");
        Object idValue = detail.get("id");
        Long id = (idValue instanceof Number) ? ((Number) idValue).longValue() : null;
        var user = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("There is no user with such id."));
        var userAuthenticated = (Users) authentication.getPrincipal();
        if (!Objects.equals(user, userAuthenticated))
            throw new RuntimeException("You don´t have permissions to edit the user. Try your own user");

        Object birthdateValue = detail.get("birthdate");

        LocalDate birthdate = (birthdateValue instanceof String)
                ? LocalDate.parse((String) birthdateValue, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                : null;

        user.setBirthdate(birthdate);
        user.setName((String) detail.get("name"));
        user.setSurname((String) detail.get("surname"));
        user.setAge(Period.between(user.getBirthdate(), LocalDate.now()).getYears());
    }

    @Override
    @Transactional
    public void deleteUser(Authentication authentication) {
        var userAuthenticated = (Users) authentication.getPrincipal();
        var user = repository.findById(userAuthenticated.getId()).orElseThrow(
                () -> new EntityNotFoundException("User does not exist."));

        user.setActive(false);
    }

    @Override
    public CreatedUserDto getUserCreatedDetails(Long id) {
        var user = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );

        return new CreatedUserDto(user);
    }
}
