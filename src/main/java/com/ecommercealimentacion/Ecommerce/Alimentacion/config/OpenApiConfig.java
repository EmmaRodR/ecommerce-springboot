package com.ecommercealimentacion.Ecommerce.Alimentacion.config;

import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;



@Configuration
@OpenAPIDefinition(
       
)

@SecurityScheme(
        name = "Bearer Authentication",
        description = "Token JWT que permite la autorizacion al acceso a los recursos. Este puede ser solicitado al iniciar sesion desde el endpoint de autentitacion.",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
    

