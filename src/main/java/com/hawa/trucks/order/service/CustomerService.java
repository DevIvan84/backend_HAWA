package com.hawa.trucks.order.service;

import com.hawa.trucks.order.dto.CustomerDTO;
import com.hawa.trucks.order.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer createCustomer(CustomerDTO customerDTO);
    Customer updateCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);
}
