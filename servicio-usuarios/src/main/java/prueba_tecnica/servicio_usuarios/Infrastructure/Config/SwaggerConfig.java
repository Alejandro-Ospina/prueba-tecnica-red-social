package prueba_tecnica.servicio_usuarios.Infrastructure.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("Microservicio de usuarios - Red social project OpenAPI Docs")
                        .version("1.0.0")
                        .description("""
                                Aquí se detalla la forma en la cual se debe consumir el servicio de usuarios, el cual se encarga de la creación,
                                administración y gestión de autenticación de los mismos a través de una red social.
                                Para esta prueba se usa JWT como estándar de autenticación en servicios REST.
                                """)
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .termsOfService("""
                                    Esta es una API de prueba que funciona como base para una aplicación escablable por
                                    medio de arquitectura basada en microservicios.
                                """)
                );
    }
}
