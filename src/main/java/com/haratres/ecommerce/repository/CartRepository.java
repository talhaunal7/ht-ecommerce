package com.haratres.ecommerce.repository;

import com.haratres.ecommerce.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
