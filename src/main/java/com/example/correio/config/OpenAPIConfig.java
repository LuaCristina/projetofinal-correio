package com.example.correio.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenAPIConfig {

    @Bean
    public OpenAPI springCorreioOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API Correio")
                        .description("API para gerenciamento de correios")
                        .version("v1.0.0"));
    }
}
