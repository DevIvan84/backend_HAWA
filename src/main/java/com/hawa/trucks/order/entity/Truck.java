package com.hawa.trucks.order.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Truck {

    public static final String BRAND_IDENTIFIER = "HAWA";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String model;
    private BigDecimal price;
    private int stock;
    private BigDecimal discount;

    @Column(nullable = false)
    private String brand = BRAND_IDENTIFIER;  // Default value for brand
}
