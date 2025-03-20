package prueba_tecnica.servicio_usuarios.Infrastructure.Adapters.Inputs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import prueba_tecnica.servicio_usuarios.Application.Dtos.LoginDto;
import prueba_tecnica.servicio_usuarios.Application.Dtos.LoginResponseDto;
import prueba_tecnica.servicio_usuarios.Application.Dtos.UserDetailsDto;
import prueba_tecnica.servicio_usuarios.Application.Services.JwtService;
import prueba_tecnica.servicio_usuarios.Application.Services.UsersService;
import prueba_tecnica.servicio_usuarios.Domain.Models.Users;

import java.net.URI;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/users")
public class UsersController {

    private final UsersService service;
    private final AuthenticationManager manager;
    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDetailsDto dto, UriComponentsBuilder builder){
        var userDto = service.createUser(dto);

        URI location = builder.path("/users/details/{id}")
                .buildAndExpand(userDto.id())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getUserCreatedDetails(@PathVariable @Positive Long id){
        return ResponseEntity.ok().body(service.getUserCreatedDetails(id));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsers(@RequestBody @Valid LoginDto dto){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
        );

        var authenticateUser = manager.authenticate(authentication);
        var setAccessTokenUser = jwtService.getToken((Users) authenticateUser.getPrincipal());
        return ResponseEntity.ok().body(new LoginResponseDto(setAccessTokenUser));
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid Map<String, Object> details,
                                        Authentication authentication,
                                        UriComponentsBuilder builder){
        service.updateUser(details, authentication);
        var user = (Users) authentication.getPrincipal();
        URI location = builder.path("/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.ok().location(location).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(Authentication authentication){
        service.deleteUser(authentication);
        return ResponseEntity.accepted().body("User deleted successfully.");
    }
}
