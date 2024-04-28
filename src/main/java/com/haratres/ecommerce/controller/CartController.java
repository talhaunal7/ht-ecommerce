package com.haratres.ecommerce.controller;


import com.haratres.ecommerce.controller.request.AddProductRequest;
import com.haratres.ecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody AddProductRequest addProductRequest){
        cartService.add(addProductRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> remove(@PathVariable Long productId){
        cartService.remove(productId);
        return ResponseEntity.ok().build();
    }

}
