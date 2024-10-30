package com.ecommercealimentacion.Ecommerce.Alimentacion.config;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.securityExceptions.CustomAccessDeniedHandler;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.securityExceptions.CustomAuthenticationEntryPoint;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.enums.Permission;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityFilter {


    private AuthenticationProvider authenticationProvider;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private CustomAccessDeniedHandler customAccessDeniedHandler;

    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    public SecurityFilter(AuthenticationProvider authenticationProvider, 
                           JwtAuthenticationFilter jwtAuthenticationFilter,
                           CustomAccessDeniedHandler customAccessDeniedHandler,
                           CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        }
    


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandlingConfig -> exceptionHandlingConfig.authenticationEntryPoint(customAuthenticationEntryPoint)
                                                                                     .accessDeniedHandler(customAccessDeniedHandler))
                .authorizeHttpRequests(authConfig -> {

                    //Authentication Swagger
                    authConfig.requestMatchers(
                        "/v3/api-docs/**",
                        "/swagger-ui/**",                                                           
                        "/swagger-ui.html"
                    ).permitAll();

                    //Authentication paths

                    authConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/register").permitAll();
                    authConfig.requestMatchers("/error").permitAll();
                    authConfig.requestMatchers(HttpMethod.GET,"/auth/guest-token").permitAll();

                    //CRUD paths - Products
                    authConfig.requestMatchers(HttpMethod.GET, "api/v1/products/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "api/v1/products/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.PATCH, "api/v1/products/**").hasAuthority(Permission.MODIFY_ALL_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "api/v1/products/**").hasAuthority(Permission.MODIFY_ALL_PRODUCT.name());

                    //CRUD paths - Categories
                    authConfig.requestMatchers(HttpMethod.GET, "api/v1/categories/**").hasAuthority(Permission.READ_ALL_CATEGORIES.name());
                    authConfig.requestMatchers(HttpMethod.POST, "api/v1/categories/**").hasAuthority(Permission.MODIFY_ALL_CATEGORIES.name());
                    authConfig.requestMatchers(HttpMethod.PATCH, "api/v1/categories/**").hasAuthority(Permission.MODIFY_ALL_CATEGORIES.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "api/v1/categories/**").hasAuthority(Permission.MODIFY_ALL_CATEGORIES.name());

                    //CRUD paths - Cart

                    authConfig.requestMatchers(HttpMethod.GET, "api/v1/cart/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "api/v1/cart/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.DELETE, "api/v1/cart/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.PATCH, "api/v1/cart/**").permitAll();

                 //CRUD paths - Orders

                    authConfig.requestMatchers(HttpMethod.GET, "api/v1/orders/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST, "api/v1/orders/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.PATCH, "api/v1/orders/**").permitAll();
                    authConfig.requestMatchers(HttpMethod.DELETE, "api/v1/orders/**").permitAll();

                    authConfig.anyRequest().authenticated();

                });


        return http.build();


    }
}