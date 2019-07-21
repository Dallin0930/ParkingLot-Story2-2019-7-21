package com.example.demo.controller;

import com.example.demo.modle.ParkingLot;
import com.example.demo.modle.ParkingLotOrder;
import com.example.demo.service.ParkingLotOrderService;
import com.example.demo.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;
import java.util.List;

@RestController
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;
    @Autowired
    ParkingLotOrderService parkingLotOrderService;


//    @GetMapping(value = "/parkinglots",params = {"page","pageSize"})
//    public List<ParkingLot> getParkingLots(@PathVariable(name = "page")Integer page,@PathVariable("pageSize")Integer pageSize){
//        return parkingLotService.getParkingLots(page,pageSize);
//    }


    @PutMapping("/parkingLots/{id}")
    public ParkingLot updateParkingLot(@PathVariable("id")Long id,@RequestBody ParkingLot parkingLot){
        return parkingLotService.updateParkingLot(id,parkingLot);
    }


//    @PostMapping("/orders/parkingLots/{id}")
//    public ParkingLotOrder addOrder(@PathVariable("id")Long id, @RequestBody ParkingLotOrder order){
//        return parkingLotOrderService.addOrder(id,order);
//    }
//
//    @PatchMapping("/orders/{id}")
//    public ParkingLotOrder updateStatus(@PathVariable Long id){
//        return (ParkingLotOrder) parkingLotOrderService.updateStatus(id);
//    }



}
