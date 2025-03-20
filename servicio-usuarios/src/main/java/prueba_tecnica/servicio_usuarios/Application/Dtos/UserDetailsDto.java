package prueba_tecnica.servicio_usuarios.Application.Dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserDetailsDto(
        @NotEmpty
        String name,

        @NotEmpty
        String surname,

        @NotNull

        @JsonFormat (pattern = "yyyy-MM-dd")
        LocalDate birthdate,

        @NotEmpty
        String country,

        @NotEmpty
        @Email
        String email,

        @NotEmpty
        String pass
) {
}
