package com.ecommercealimentacion.Ecommerce.Alimentacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
public class EcommerceAlimentacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAlimentacionApplication.class, args);

	}

}
