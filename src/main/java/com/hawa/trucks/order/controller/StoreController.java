package com.hawa.trucks.order.controller;

import com.hawa.trucks.order.dto.StoreDTO;
import com.hawa.trucks.order.entity.Store;
import com.hawa.trucks.order.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@AllArgsConstructor
public class StoreController {

    private StoreService storeService;

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Long id) {
        return storeService.getStoreById(id);
    }

    @PostMapping
    public Store createStore(@RequestBody StoreDTO storeDTO) {
        return storeService.createStore(storeDTO);
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @RequestBody StoreDTO storeDTO) {
        return storeService.updateStore(id, storeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok().build();
    }
}
