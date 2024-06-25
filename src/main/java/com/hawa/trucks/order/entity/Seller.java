package com.hawa.trucks.order.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    private Store store;
}
