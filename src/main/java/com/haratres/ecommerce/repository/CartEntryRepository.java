package com.haratres.ecommerce.repository;

import com.haratres.ecommerce.domain.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {
}
