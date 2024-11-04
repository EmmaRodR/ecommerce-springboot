package com.ecommercespringboot.models.dtos.responses;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {

    private HttpStatus statusCode;

    private String msg;

    private LocalDateTime timeStamp;

    public ErrorResponseDto(HttpStatus statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.timeStamp = LocalDateTime.now();
    }

    public ErrorResponseDto () {}


}
