package com.hawa.trucks.order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TruckDTO {

    private String name;
    private String model;
    private BigDecimal price;
    private int stock;
    private BigDecimal discount;
}
