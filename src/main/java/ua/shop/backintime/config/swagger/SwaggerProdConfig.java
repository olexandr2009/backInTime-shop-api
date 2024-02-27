package ua.shop.backintime.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

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
<<<<<<< HEAD:src/main/java/ua/shop/backintime/config/swagger/SwaggerProdConfig.java
=======
        servers = @Server(
                url = "https://back-in-time-shop-api.onrender.com"
        ),
>>>>>>> 537e4547a1ddea0ae4749a139bfd3aafb9120e86:src/main/java/ua/shop/backintime/config/SwaggerConfig.java
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }

)
//@Configuration
public class SwaggerProdConfig {
}