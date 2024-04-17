package com.haratres.ecommerce.service;

import com.haratres.ecommerce.controller.request.CreateProductRequest;
import com.haratres.ecommerce.controller.response.ProductDto;
import com.haratres.ecommerce.domain.Product;
import com.haratres.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(CreateProductRequest createProductRequest){
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());
        productRepository.save(product);
    }

    public ProductDto get(Long id){
        Product product=findById(id);
        return ProductDto.convertToDto(product);
    }


    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product.not.found"));
    }
}
