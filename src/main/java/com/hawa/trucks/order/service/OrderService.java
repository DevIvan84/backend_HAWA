package com.hawa.trucks.order.service;

import com.hawa.trucks.order.dto.OrderDTO;
import com.hawa.trucks.order.entity.Orders;
import com.hawa.trucks.order.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    List<Orders> getAllOrders();
    Orders getOrderById(Long id);
    Orders createOrder(OrderDTO orderDTO);
    Orders updateOrderStatus(Long id, OrderStatus status);


}
