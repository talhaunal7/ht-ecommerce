package com.haratres.ecommerce.controller;

import com.haratres.ecommerce.controller.request.CreateProductRequest;
import com.haratres.ecommerce.controller.response.ProductDto;
import com.haratres.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    //@PreAuthorize("hasAuthority('ADMIN')")
    public void create(@Valid @RequestBody CreateProductRequest request){
        productService.create(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductDto get(@PathVariable Long id){
        return productService.get(id);
    }




}
