package com.ecommercespringboot.utils.mappers;
import org.springframework.data.domain.Page;

import com.ecommercespringboot.models.dtos.responses.PageResponse;


public class PageResponseMapper {

    public static PageResponse converToPageResponse(Page<?> page) {
        return PageResponse.builder()
                .pageNumber(page.getPageable().getPageNumber())
                .pageSize(page.getPageable().getPageSize())
                .numberOfElements(page.getNumberOfElements())
                .content(page.getContent())
                .build();


    }




}
