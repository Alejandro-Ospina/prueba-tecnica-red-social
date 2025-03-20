package prueba_tecnica.servicio_publicaciones.Application.Dtos;

import org.springframework.validation.FieldError;

public record FieldErrorDto(
        String field,
        String error
) {
    public FieldErrorDto(FieldError error){
        this(
                error.getField(),
                error.getDefaultMessage()
        );
    }
}
