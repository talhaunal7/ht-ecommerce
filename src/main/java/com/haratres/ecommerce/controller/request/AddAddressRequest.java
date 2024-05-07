package com.haratres.ecommerce.controller.request;

import jakarta.validation.constraints.NotBlank;

public class AddAddressRequest {

    @NotBlank
    private String city;
    @NotBlank
    private String street;

    public AddAddressRequest(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
