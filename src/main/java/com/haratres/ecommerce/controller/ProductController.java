package com.haratres.ecommerce.controller;

import com.haratres.ecommerce.config.AuthUtil;
import com.haratres.ecommerce.controller.request.CreateProductRequest;
import com.haratres.ecommerce.controller.response.ProductDto;
import com.haratres.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void create(@RequestBody CreateProductRequest request){
        productService.create(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductDto get(@PathVariable Long id){
        return productService.get(id);
    }





}
