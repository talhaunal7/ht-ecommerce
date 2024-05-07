package com.haratres.ecommerce.service;

import com.haratres.ecommerce.config.AuthUtil;
import com.haratres.ecommerce.controller.AddressController;
import com.haratres.ecommerce.controller.request.AddAddressRequest;
import com.haratres.ecommerce.domain.Address;
import com.haratres.ecommerce.domain.Cart;
import com.haratres.ecommerce.domain.User;
import com.haratres.ecommerce.repository.AddressRepository;
import com.haratres.ecommerce.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;

    public AddressService(CartRepository cartRepository, AddressRepository addressRepository) {
        this.cartRepository = cartRepository;
        this.addressRepository = addressRepository;
    }

    public void addAddress(AddAddressRequest request) {
        User user = AuthUtil.getAuthenticatedUser();
        Cart cart = Optional
                .ofNullable(user.getCart())
                .orElseThrow(() -> new EntityNotFoundException("cart not found"));

        Address address = new Address();
        address.setCity(request.getCity());
        address.setStreet(request.getStreet());

        List<Address> addressList = addressRepository.findByCart_Id(cart.getId());
        addressList.add(address);

        cart.getAddresses().add(address);
        address.setCart(cart);

        addressRepository.save(address);
        cartRepository.save(cart);
    }

}
