package com.ecommercespringboot.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    //Configuracion para implementar ModelMapper
    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();

    }

    
}
