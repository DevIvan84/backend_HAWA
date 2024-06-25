package com.hawa.trucks.order.service;

import com.hawa.trucks.order.dto.SellerDTO;
import com.hawa.trucks.order.entity.Seller;

import java.util.List;

public interface SellerService {

    List<Seller> getAllSellers();
    Seller getSellerById(Long id);
    Seller createSeller(SellerDTO sellerDTO);
    Seller updateSeller(Long id, SellerDTO sellerDTO);
    void deleteSeller(Long id);
}
