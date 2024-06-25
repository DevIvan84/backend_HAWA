package com.hawa.trucks.order.service.impl;

import com.hawa.trucks.order.dto.SellerDTO;
import com.hawa.trucks.order.entity.Store;
import com.hawa.trucks.order.exceptions.ResourceNotFoundException;
import com.hawa.trucks.order.repository.SellerRepository;
import com.hawa.trucks.order.repository.StoreRepository;
import com.hawa.trucks.order.entity.Seller;
import com.hawa.trucks.order.service.SellerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {

    private SellerRepository sellerRepository;

    private StoreRepository storeRepository;

    private ModelMapper modelMapper;

    @Override
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
    }

    @Override
    public Seller createSeller(SellerDTO sellerDTO) {

        Seller seller = modelMapper.map(sellerDTO, Seller.class);
        seller.setStore(storeRepository.findById(sellerDTO.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found")));
        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSeller(Long id, SellerDTO sellerDTO) {

        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found"));



        seller.setName(sellerDTO.getName());

        Store store = storeRepository.findById(sellerDTO.getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        seller.setStore(store);


        return sellerRepository.save(seller);

    }

    @Override
    public void deleteSeller(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Seller not found"));
        sellerRepository.delete(seller);
    }
}
