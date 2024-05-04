package com.haratres.ecommerce.controller.request;

import jakarta.validation.constraints.NotBlank;

public class CreateProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
