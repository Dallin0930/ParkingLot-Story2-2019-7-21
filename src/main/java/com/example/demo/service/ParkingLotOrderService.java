package com.example.demo.service;

import com.example.demo.modle.ParkingLotOrder;
import com.example.demo.repository.ParkingLotOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotOrderService{
    @Autowired
    ParkingLotOrderRepository parkingLotOrderRepository;

    public ParkingLotOrder geOrder(long parkingLotID, long parkingOrderId) {
        return parkingLotOrderRepository.findById(parkingOrderId).orElse()
    }

}
