package com.haratres.ecommerce.controller;

import com.haratres.ecommerce.controller.request.AddAddressRequest;
import com.haratres.ecommerce.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<Void> add(@Valid @RequestBody AddAddressRequest request) {
        addressService.addAddress(request);
        return ResponseEntity.ok().build();
    }
}
