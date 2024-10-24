package com.ecommercealimentacion.Ecommerce.Alimentacion.controllers;

import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.categories.CategoryRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.categories.CategoryResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.BaseResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.PageResponse;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Categories", description = "Create, read, update or delete categories")
@RestController
@RequestMapping("api/v1/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {

    ICategoryService service;

    public CategoryController(ICategoryService service) {
        this.service = service;
    }

    @Operation(summary = "Get all categories", description = "Get all categories and their information")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = CategoryResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<PageResponse> getAll(
            @RequestParam(value = "Page Number", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "Page Size", required = false, defaultValue = "20") int pageSize

    ) throws NoElementException {

        Pageable page = PageRequest.of(pageNumber, pageSize);

        return ResponseEntity.ok(service.get(page));
    }

    @Operation(summary = "Create a category", description = "Add categories for the Ecommerce App", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Producto a ser creado"))
    @ApiResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = CategoryResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<BaseResponseDto<CategoryResponseDto>> create(
            @RequestBody CategoryRequestDto categoryRequestDto) throws ElementAlreadyExistsException {
        return service.create(categoryRequestDto);
    }

    @Operation(summary = "Delete a category", description = "Delete categories in the Ecommerce App")
    @ApiResponse(responseCode = "204", description = "No Content", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws NoElementException {

        return service.delete(id);
    }

    @Operation(summary = "Update a category", description = "Update categories for the Ecommerce App")
    @ApiResponse(responseCode = "200", description = "Updated", content = @Content(schema = @Schema(implementation = CategoryResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @PatchMapping("{id}")
    public ResponseEntity<BaseResponseDto<CategoryResponseDto>> update(@PathVariable Long id,
            @RequestBody CategoryRequestDto categoryRequestDto) throws NoElementException {
        return service.update(id, categoryRequestDto);
    }

    @Operation(summary = "Get categories by id", description = "Get category by his id")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = CategoryResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) throws NoElementException {

        return service.getCategoryById(id);
    }

}
