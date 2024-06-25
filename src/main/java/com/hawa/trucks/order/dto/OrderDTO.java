package com.hawa.trucks.order.dto;

import com.hawa.trucks.order.entity.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Long customerId;
    private Long sellerId;
    private Long storeId;
    private List<Long> truckIds;
}
