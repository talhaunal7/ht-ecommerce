package com.haratres.ecommerce.repository;

import com.haratres.ecommerce.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCart_Id(Long id);


}
