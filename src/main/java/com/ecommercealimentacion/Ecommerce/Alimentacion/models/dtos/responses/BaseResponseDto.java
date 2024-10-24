package com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
public class BaseResponseDto<T> {

    private LocalDateTime timestamp;

    private HttpStatus status;
    private T data;

    private Long itemCount;

    public BaseResponseDto(T data,HttpStatus status) {
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.status = status;

    }


    public BaseResponseDto(T data,HttpStatus status,Long itemCount) {
        this.itemCount = itemCount;
        this.data = data;
        this.timestamp = LocalDateTime.now();
        this.status = status;

    }


    public BaseResponseDto() {
    }


    public static <T> ResponseEntity<BaseResponseDto<T>> build (T data, HttpStatus status) {
        return new ResponseEntity<>(new BaseResponseDto<>(data,status),status);
    }

    public static <T> ResponseEntity<BaseResponseDto<T>> build (T data, HttpStatus status,Long itemCount) {
        return new ResponseEntity<>(new BaseResponseDto<>(data,status,itemCount),status);
    }


}
