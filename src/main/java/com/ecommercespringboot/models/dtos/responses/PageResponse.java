package com.ecommercespringboot.models.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse{

    private int pageNumber;
    private int pageSize;
    private int numberOfElements;
    private List<?> content;

}