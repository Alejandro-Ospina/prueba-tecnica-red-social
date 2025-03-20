package prueba_tecnica.servicio_publicaciones.Application.Advices;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.FieldErrorDto;
import prueba_tecnica.servicio_publicaciones.Application.Dtos.MessageDto;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler (EntityExistsException.class)
    public ResponseEntity<?> handleEntityExistsException(EntityExistsException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public ResponseEntity<?> handleMethodArgumentException(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors().stream().map(FieldErrorDto::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler (RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex){
        return ResponseEntity.badRequest().body(new MessageDto(LocalDateTime.now(), ex.getMessage()));
    }

    @ExceptionHandler (Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        return ResponseEntity.internalServerError().body(new MessageDto(LocalDateTime.now(), ex.getMessage()));
    }
}
