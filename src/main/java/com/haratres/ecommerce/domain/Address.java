package com.haratres.ecommerce.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    //todo isActive

    public Address() {
    }

    public Address(Long id, String city, String street) {
        this.id = id;
        this.city = city;
        this.street = street;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public static final class AddressBuilder {
        private String city;
        private String street;
        private Cart cart;

        private AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder withStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder withCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.setCity(city);
            address.setStreet(street);
            address.setCart(cart);
            return address;
        }
    }
}