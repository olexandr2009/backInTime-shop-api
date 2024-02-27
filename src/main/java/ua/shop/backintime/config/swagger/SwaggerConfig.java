package ua.shop.backintime.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition(
        info = @Info(
                title = "Open source Shop api",
                description = "Shop Api",
                version = "V1",
                contact = @Contact(
                        name = "Olexandr Khrystevich",
                        email = "hristevich.ua@gmail.com"
                )
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }

)
@Configuration
public class SwaggerConfig {
}