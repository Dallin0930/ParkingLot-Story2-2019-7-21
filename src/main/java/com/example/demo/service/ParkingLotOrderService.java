package com.example.demo.service;


import com.example.demo.exception.NotEnoughCapacityException;
import com.example.demo.modle.ParkingLot;
import com.example.demo.modle.ParkingLotOrder;
import com.example.demo.repository.ParkingLotOrderRepository;
import com.example.demo.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;

@Service
public class ParkingLotOrderService{
    @Autowired
    ParkingLotOrderRepository parkingLotOrderRepository;
    @Autowired
    ParkingLotRepository parkingLotRepository;



    @Transient
    public ParkingLotOrder addOrder(Long id, ParkingLotOrder order) {
        ParkingLot parkingLot = parkingLotRepository.findById(id).orElse(null);
        if (parkingLot!=null && parkingLot.getCapacity()>0){
            order.setParkinglot(parkingLot);
            parkingLot.setCapacity(parkingLot.getCapacity()-1);
            order.setEnterTime(new Date().getTime());
            order.setStatus("ACTIVE");
        }else{
            throw new NotEnoughCapacityException("停车场已经满");
        }
        return (ParkingLotOrder) parkingLotOrderRepository.save(order);
    }


    @Transient
    public ParkingLotOrder updateStatus(Long id) {
        ParkingLotOrder order = (ParkingLotOrder) parkingLotOrderRepository.findById(id).orElse(null);
        order.setStatus("used");
        ParkingLot parkingLot = order.getParkinglot();
        parkingLot.setCapacity(parkingLot.getCapacity()+1);
        return (ParkingLotOrder) parkingLotOrderRepository.save(order);
    }


}
