package com.ecommercealimentacion.Ecommerce.Alimentacion.controllers;


import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.ElementAlreadyExistsException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.exceptions.especificExceptions.NoElementException;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products.ProductRequestDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.products.ProductResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.BaseResponseDto;
import com.ecommercealimentacion.Ecommerce.Alimentacion.models.dtos.responses.PageResponse;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.IProductService;
import com.ecommercealimentacion.Ecommerce.Alimentacion.services.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Products",description = "Create, read, update or delete products")
@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
public class ProductController {

    
    IProductService service;


    public ProductController (ProductServiceImpl service) {
        this.service = service;
    }


    @Operation(summary = "Get all products", description = "Get all products and their information")
    @ApiResponse(responseCode = "200", description = "Ok", content =  @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content =  @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content =  @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping
    public ResponseEntity<PageResponse> getAll (
            @RequestParam(value = "pageSize", required = false, defaultValue ="50") int pageSize,
            @RequestParam(value = "pageNumber", required = false, defaultValue ="0") int pageNumber
    ) throws Exception {

        Pageable page = PageRequest.of(pageNumber,pageSize);


        return ResponseEntity.ok(service.get(page));

    }


    @Operation(summary = "Get products by category", description = "Get all the products on a specific category")
    @ApiResponse(responseCode = "200", description = "Ok", content =  @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content =  @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content =  @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/productsByCategory")
    public List<ProductResponseDto> getProductsByCategory (@RequestParam String name) throws NoElementException {

        return service.getProductsByCategory(name);

    }

    @Operation(summary = "Get products by name", description = "Get a product by his name")
    @ApiResponse(responseCode = "200", description = "Ok", content =  @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content =  @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content =  @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/productsByName")
    public List<ProductResponseDto> getProductByName (@RequestParam String name) throws NoElementException {

        return service.getProductsByName(name);
    }

    @Operation(summary = "Get products by id", description = "Get a product by his id")
    @ApiResponse(responseCode = "200", description = "Ok", content =  @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content =  @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content =  @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/{id}")
    public ProductResponseDto getProductById (@PathVariable Long id) throws NoElementException {

        return service.getProductsById(id);
    }


    @Operation(summary = "Create a product", description = "Add products for the Ecommerce App")
    @ApiResponse(responseCode = "201", description = "Created", content =  @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content =  @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content =  @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping
    public ResponseEntity<BaseResponseDto<ProductResponseDto>> create (@RequestBody @Valid ProductRequestDto productRequestDto) throws ElementAlreadyExistsException, NoElementException {

        return service.create(productRequestDto);
    }

    @Operation(summary = "Update a product", description = "Update products for the Ecommerce App")
    @ApiResponse(responseCode = "201", description = "Updated", content =  @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content =  @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content =  @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponseDto<ProductResponseDto>> update (@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) throws NoElementException{
        return service.update(id,productRequestDto);

    }

    @Operation(summary = "Delete a product", description = "Delete products in the Ecommerce App")
    @ApiResponse(responseCode = "204", description = "No Content", content =  @Content(schema = @Schema(implementation = ProductResponseDto.class)))
    @ApiResponse(responseCode = "400", description = "Bad Request", content =  @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content =  @Content)
    @SecurityRequirement(name = "Bearer Authentication")
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "https://ecommerce-reactfrontend.onrender.com/")
    public ResponseEntity<?> delete (@PathVariable Long id) throws NoElementException {
        return service.delete(id);
    }

}
