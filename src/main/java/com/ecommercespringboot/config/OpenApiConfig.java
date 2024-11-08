package com.ecommercespringboot.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Ecommerce API Spring Boot",
        version = "1.0",
        description = """
        
        API CRUD para gestionar una tienda online, incluyendo endpoints de productos, categorías y autenticación.",
        
            Usuarios de prueba:
             - Admin
             - Usuario: `Admin`
             - Contraseña: `Admin12345`
            
            Custom Client
             - Usuario: `Customer`
             - Contraseña: `Customer12345`

            Utiliza estos usuarios para probar los diferentes roles y permisos dentro de la API.
         """,
                
        
        contact = @Contact(
            name = "Emmanuel Rodriguez",
            email = "emmanuelrgeek@gmail.com",
            url = "https://www.linkedin.com/in/emmanuelrodr%C3%ADguezbuzzo/"
        ),
        license = @License(
            name = "Github",
            url = "https://github.com/EmmaRodR"
        )
    )
)
@SecurityScheme(
    name = "Bearer Authentication", 
    description = "Token JWT que permite la autorización al acceso a los recursos. Este puede ser solicitado al iniciar sesión desde el endpoint de autenticación.", 
    type = SecuritySchemeType.HTTP, 
    bearerFormat = "JWT", 
    scheme = "bearer"
)
public class OpenApiConfig {
}