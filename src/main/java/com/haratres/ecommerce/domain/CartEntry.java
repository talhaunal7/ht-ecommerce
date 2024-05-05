package com.haratres.ecommerce.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "cart_entry")
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CartEntry() {
    }

    public CartEntry(Product product) {
        this.product = product;
    }

    public CartEntry(Long id, Product product) {
        this.id = id;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
