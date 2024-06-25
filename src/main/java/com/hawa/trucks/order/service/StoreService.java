package com.hawa.trucks.order.service;

import com.hawa.trucks.order.dto.StoreDTO;
import com.hawa.trucks.order.entity.Store;

import java.util.List;

public interface StoreService {

    List<Store> getAllStores();
    Store getStoreById(Long id);
    Store createStore(StoreDTO storeDTO);
    Store updateStore(Long id, StoreDTO storeDTO);
    void deleteStore(Long id);
}
