package com.haratres.ecommerce.controller;


import com.haratres.ecommerce.controller.request.AddProductRequest;
import com.haratres.ecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/carts/{cartId}/products")
public class CartProductController {

    private final CartService cartService;

    public CartProductController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody AddProductRequest addProductRequest, @PathVariable Long cartId){
        cartService.add(cartId, addProductRequest);
        return ResponseEntity.ok().build();
    }

}
