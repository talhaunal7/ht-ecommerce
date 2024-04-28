package com.haratres.ecommerce.config;

import com.haratres.ecommerce.domain.Cart;
import com.haratres.ecommerce.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class AuthUtil {

    private AuthUtil() {
    }

    public static User getAuthenticatedUser() {
        var user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.isNull(user)) {
            throw new RuntimeException("auth.error");
        }
        return user;
    }

    public static Long getAuthenticatedUserId() {
        var user = getAuthenticatedUser();
        return user.getId();
    }


    public static Optional<Cart> getAuthenticatedUserCart() {
        var user = getAuthenticatedUser();
        return Optional.ofNullable(user.getCart());
    }
}