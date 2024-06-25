package com.hawa.trucks.order.service.impl;

import com.hawa.trucks.order.dto.OrderDTO;
import com.hawa.trucks.order.entity.Orders;
import com.hawa.trucks.order.entity.Truck;
import com.hawa.trucks.order.entity.enums.OrderStatus;
import com.hawa.trucks.order.exceptions.BadRequestException;
import com.hawa.trucks.order.exceptions.ResourceNotFoundException;
import com.hawa.trucks.order.service.OrderService;
import com.hawa.trucks.order.repository.*;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private TruckRepository truckRepository;

    private CustomerRepository customerRepository;

    private SellerRepository sellerRepository;

    private StoreRepository storeRepository;

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Orders getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public Orders createOrder(OrderDTO orderDTO) {

        List<Truck> trucks = new ArrayList<>();

        for (Long truckId : orderDTO.getTruckIds()) {
            Truck truck = truckRepository.findById(truckId)
                    .orElseThrow(() -> new ResourceNotFoundException("Truck not found with id: " + truckId));
            if (truck.getStock() <= 0) {
                throw new BadRequestException("Truck " + truck.getName() + " is not available in stock");
            }
            trucks.add(truck);
        }

        Orders order = new Orders();
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDateTime.now());
        order.setCustomer(customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + orderDTO.getCustomerId())));
        order.setSeller(sellerRepository.findById(orderDTO.getSellerId())
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found with id: " + orderDTO.getSellerId())));
        order.setStore(storeRepository.findById(orderDTO.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found with id: " + orderDTO.getStoreId())));
        order.setTrucks(trucks);

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Truck truck : trucks) {
            totalPrice = totalPrice.add(truck.getPrice().subtract(truck.getDiscount()));
            truck.setStock(truck.getStock() - 1);
            truckRepository.save(truck);
        }
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);

    }

    @Override
    public Orders updateOrderStatus(Long id, OrderStatus status) {

        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus() == OrderStatus.PENDING && (status == OrderStatus.DELIVERED || status == OrderStatus.CANCELLED)) {
            order.setStatus(status);
        } else {
            throw new BadRequestException("Invalid status change");
        }

        if (status == OrderStatus.CANCELLED) {
            for (Truck truck : order.getTrucks()) {
                truck.setStock(truck.getStock() + 1);
                truckRepository.save(truck);
            }
        }

        return orderRepository.save(order);

    }
}
