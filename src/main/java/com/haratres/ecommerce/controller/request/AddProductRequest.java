package com.haratres.ecommerce.controller.request;

import jakarta.validation.constraints.Positive;

public class AddProductRequest {

    @Positive
    private Long productId;

    @Positive
    private int quantity;

    public AddProductRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
