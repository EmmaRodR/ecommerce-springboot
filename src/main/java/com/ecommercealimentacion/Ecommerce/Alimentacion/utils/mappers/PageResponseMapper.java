package com.ecommercealimentacion.Ecommerce.Alimentacion.utils.mappers;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.PageResponse;
import org.springframework.data.domain.Page;


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
