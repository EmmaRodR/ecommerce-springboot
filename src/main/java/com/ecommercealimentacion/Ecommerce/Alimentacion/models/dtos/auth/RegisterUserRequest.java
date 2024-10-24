package com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.auth;

import com.ecommercealimentacion.Ecommerce.Alimentacion.models.enums.Role;

import jakarta.validation.constraints.NotBlank;


public class RegisterUserRequest {

    @NotBlank(message = "Name is mandatory")
    private String username;
    
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotBlank(message = "Verify Password is mandatory")
    private String passwordVerify;

    private Role role;

    public RegisterUserRequest(String username,String email, String password, String passwordVerify,Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordVerify = passwordVerify;
        this.role = Role.CUSTOMER;
       
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerify() {
        return passwordVerify;
    }

    public void setPasswordVerify(String passwordVerify) {
        this.passwordVerify = passwordVerify;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    

}