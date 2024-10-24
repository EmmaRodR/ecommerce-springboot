package com.ecommercealimentacion.Ecommerce.Alimentacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:5173")
public class EcommerceAlimentacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAlimentacionApplication.class, args);

	}

}
