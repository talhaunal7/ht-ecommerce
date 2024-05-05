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
import jakarta.persistence.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException("product not found"));

        User user = AuthUtil.getAuthenticatedUser();
        Cart cart = Optional
                .ofNullable(user.getCart())
                .orElseGet(Cart::new);

        CartEntry cartEntry = new CartEntry();
        boolean isProductPresent = false;
        for (CartEntry ce : cart.getCartEntries()) {
            if (!Objects.isNull(ce.getProduct())) {
                if (ce.getProduct().getId().equals(product.getId())) {
                    isProductPresent = true;
                    cartEntry = ce;
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

        cartRepository.save(cart);
        cartEntryRepository.save(cartEntry);
        userRepository.save(user);
    }

    public void remove(Long productId) {
        User user = AuthUtil.getAuthenticatedUser();
        Cart cart = Optional
                .ofNullable(user.getCart())
                .orElseThrow(() -> new EntityNotFoundException("cart not found"));

        Optional<CartEntry> cartEntry = cart.getCartEntries().stream()
                .filter(ce -> !Objects.isNull(ce.getProduct()))
                .filter(ce -> ce.getProduct().getId().equals(productId))
                .findFirst();

        if (cartEntry.isEmpty())
            throw new EntityNotFoundException("matching cart entry not found");

        cart.getCartEntries().remove(cartEntry.get());

        cartEntryRepository.delete(cartEntry.get());
        cartRepository.save(cart);
    }
}
