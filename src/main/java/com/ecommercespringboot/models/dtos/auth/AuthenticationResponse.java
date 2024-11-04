package com.ecommercespringboot.models.dtos.auth;

import java.time.LocalDateTime;

import com.ecommercespringboot.models.enums.Role;

public class AuthenticationResponse {

    private LocalDateTime localDateTime;
    private Long id;
    private String username;
    private String email;
    private String status;
    private String jwt;
    private Role role;

    public AuthenticationResponse(Long id,String jwt, String status, String username, String email, Role role) {
        this.localDateTime = LocalDateTime.now();
        this.id = id;
        this.status = status;
        this.username = username;
        this.email = email;
        this.jwt = jwt;
        this.role = role;
    }

  
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }




    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
    }

}
