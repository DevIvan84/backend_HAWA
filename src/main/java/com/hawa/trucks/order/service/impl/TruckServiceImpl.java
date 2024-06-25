package com.hawa.trucks.order.service.impl;


import com.hawa.trucks.order.dto.TruckDTO;
import com.hawa.trucks.order.entity.Truck;
import com.hawa.trucks.order.exceptions.ResourceNotFoundException;
import com.hawa.trucks.order.repository.TruckRepository;
import com.hawa.trucks.order.service.TruckService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TruckServiceImpl implements TruckService {

    private TruckRepository truckRepository;

    private ModelMapper modelMapper;

    @Override
    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    @Override
    public Truck getTruckById(Long id) {
        return truckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Truck not found"));
    }

    @Override
    public Truck createTruck(TruckDTO truckDTO) {

        Truck truck = modelMapper.map(truckDTO, Truck.class);

        return truckRepository.save(truck);

    }

    @Override
    public Truck updateTruck(Long id, TruckDTO truckDTO) {

        Truck truck = truckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Truck not found"));

        modelMapper.map(truckDTO, truck);

        return truckRepository.save(truck);
    }

    @Override
    public void deleteTruck(Long id) {
        Truck truck = truckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Truck not found"));
        truckRepository.delete(truck);
    }


}
