package prueba_tecnica.servicio_usuarios.Application.Dtos;

import prueba_tecnica.servicio_usuarios.Domain.Models.Users;

import java.time.LocalDate;

public record CreatedUserDto(
        Long id,
        String name,
        String surname,
        LocalDate birthdate,
        Integer age,
        String country,
        String email
) {
    public CreatedUserDto(Users user){
        this(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getBirthdate(),
                user.getAge(),
                user.getCountry(),
                user.getEmail()
        );
    }
}
