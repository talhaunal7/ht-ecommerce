package com.haratres.ecommerce.service;

import com.haratres.ecommerce.config.AuthUtil;
import com.haratres.ecommerce.controller.request.AddProductRequest;
import com.haratres.ecommerce.domain.Cart;
import com.haratres.ecommerce.domain.CartEntry;
import com.haratres.ecommerce.domain.Product;
import com.haratres.ecommerce.domain.User;
import com.haratres.ecommerce.repository.CartEntryRepository;
import com.haratres.ecommerce.repository.CartRepository;
import com.haratres.ecommerce.repository.ProductRepository;
import com.haratres.ecommerce.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartEntryRepository cartEntryRepository;


    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, CartEntryRepository cartEntryRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartEntryRepository = cartEntryRepository;
    }

    public void add(AddProductRequest addProductRequest) {

        Product product = productRepository
                .findById(addProductRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));//TODO write custom exception

        User user = AuthUtil.getAuthenticatedUser();

        Optional<Cart> optionalCart = Optional.ofNullable(user.getCart());

        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
            System.out.println("USER HAVE CART");
        } else {
            cart = new Cart();
            System.out.println("DONT HAVE CART");
        }


        //find the matching cart entry
        //Optional<CartEntry> cartEntry = Optional.empty();
        CartEntry cartEntry= new CartEntry();
        boolean isProductPresent = false;
        for (CartEntry ce : cart.getCartEntries()) {

            if (!Objects.isNull(ce.getProduct())) { // cart entry in the cart don't have product

                if (ce.getProduct().getId().equals(product.getId())) {
                    isProductPresent=true;
                    cartEntry=ce;// adresini setle
                    //cartEntry = Optional.of(ce);
                    break;
                }
            }
        }


        if (isProductPresent) {
            cartEntry.setQuantity(addProductRequest.getQuantity() + cartEntry.getQuantity());
        } else {
            cartEntry.setProduct(product);
            cartEntry.setCart(cart);
            cartEntry.setQuantity(addProductRequest.getQuantity());
            cart.getCartEntries().add(cartEntry);//x
        }
        user.setCart(cart);

        //todo check available quantity for the item + stock operations

        cartRepository.save(cart);
        cartEntryRepository.save(cartEntry);
        userRepository.save(user);


    }
}
