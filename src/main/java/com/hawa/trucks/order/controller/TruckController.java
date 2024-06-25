package com.hawa.trucks.order.controller;

import com.hawa.trucks.order.dto.TruckDTO;
import com.hawa.trucks.order.entity.Truck;
import com.hawa.trucks.order.service.TruckService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/trucks")
@AllArgsConstructor
public class TruckController {


    private TruckService truckService;

    @GetMapping
    public List<Truck> getAllTrucks() {
        return truckService.getAllTrucks();
    }

    @GetMapping("/{id}")
    public Truck getTruckById(@PathVariable Long id) {
        return truckService.getTruckById(id);
    }

    @PostMapping
    public Truck createTruck(@RequestBody TruckDTO truckDTO) {
        return truckService.createTruck(truckDTO);
    }

    @PutMapping("/{id}")
    public Truck updateTruck(@PathVariable Long id, @RequestBody TruckDTO truckDTO) {
        return truckService.updateTruck(id, truckDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTruck(@PathVariable Long id) {
        truckService.deleteTruck(id);
        return ResponseEntity.ok().build();
    }
}
