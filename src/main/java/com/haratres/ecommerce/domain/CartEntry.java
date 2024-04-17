package com.haratres.ecommerce.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "cart_entry")
public class CartEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
