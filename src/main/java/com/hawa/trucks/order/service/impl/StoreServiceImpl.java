package com.hawa.trucks.order.service.impl;

import com.hawa.trucks.order.exceptions.ResourceNotFoundException;
import com.hawa.trucks.order.repository.StoreRepository;
import com.hawa.trucks.order.dto.StoreDTO;
import com.hawa.trucks.order.entity.Store;
import com.hawa.trucks.order.service.StoreService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;

    private ModelMapper modelMapper;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(Long id) {

        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
    }

    @Override
    public Store createStore(StoreDTO storeDTO) {
        Store store = modelMapper.map(storeDTO, Store.class);

        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(Long id, StoreDTO storeDTO) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));


        modelMapper.map(storeDTO, store);

        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        storeRepository.delete(store);
    }
}
