package com.haratres.ecommerce.service;

import com.haratres.ecommerce.controller.request.AddProductRequest;
import com.haratres.ecommerce.domain.Cart;
import com.haratres.ecommerce.domain.CartEntry;
import com.haratres.ecommerce.domain.Product;
import com.haratres.ecommerce.repository.CartRepository;
import com.haratres.ecommerce.repository.ProductRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void add(Long cartId, AddProductRequest addProductRequest){
        Product product = productRepository
                .findById(addProductRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));//TODO write custom exception

        Cart cart = cartRepository.findById(cartId).orElseGet(Cart::new);

        Optional<CartEntry> cEntry= cart.getCartEntries().stream()
                .filter(cet->cet.getProduct().getId().equals(product.getId()))
                .findFirst();


        if(cEntry.isPresent()){
            CartEntry caEntry = cEntry.get();
            caEntry.setQuantity(addProductRequest.getQuantity()+caEntry.getQuantity());

        }else{
            CartEntry cartEntry=new CartEntry(product);
            cartEntry.setQuantity(addProductRequest.getQuantity());
            cart.getCartEntries().add(cartEntry);
        }

        //todo check available quantity for the item + stock operations

        cartRepository.save(cart);

    }
}
