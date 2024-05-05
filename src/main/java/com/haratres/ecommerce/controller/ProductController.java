package com.haratres.ecommerce.controller;

import com.haratres.ecommerce.controller.request.CreateProductRequest;
import com.haratres.ecommerce.controller.response.ProductDto;
import com.haratres.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void create(@Valid @RequestBody CreateProductRequest request){
        productService.create(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductDto get(@PathVariable @NotNull Long id){
        return productService.get(id);
    }




}
