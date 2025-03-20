package prueba_tecnica.servicio_usuarios.Domain.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import prueba_tecnica.servicio_usuarios.Application.Dtos.UserDetailsDto;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

@Entity
@Table (name = "users")
@Builder
@Data
@EqualsAndHashCode (of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Users implements UserDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    private Integer age;
    private String country;
    private String email;
    private String pass;
    private Boolean active;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public Users(UserDetailsDto dto){
        this.name = dto.name();
        this.surname = dto.surname();
        this.birthdate = dto.birthdate();
        this.age = Period.between(birthdate, LocalDate.now()).getYears();
        this.country = dto.country();
        this.email = dto.email();
        this.active = true;
    }
}
