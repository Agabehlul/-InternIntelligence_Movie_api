package com.aga.intern_intelligence_movie_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "MOVIE API",
                description = "INTERN_INTELLIGENCE ",
                version = "1.0.0",
                contact = @Contact(
                        name = "Agabehlul",
                        email = "agabehlul@outlook.com",
                        url = "https://github.com/agabehlul"
                )

        ),
        security = {
                @io.swagger.v3.oas.annotations.security.SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@io.swagger.v3.oas.annotations.security.SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
