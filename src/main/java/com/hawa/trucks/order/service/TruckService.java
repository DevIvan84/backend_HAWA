package com.hawa.trucks.order.service;

import com.hawa.trucks.order.dto.TruckDTO;
import com.hawa.trucks.order.entity.Truck;

import java.util.List;

public interface TruckService {

    List<Truck> getAllTrucks();
    Truck getTruckById(Long id);
    Truck createTruck(TruckDTO truckDTO);
    Truck updateTruck(Long id, TruckDTO truckDTO);
    void deleteTruck(Long id);
}
