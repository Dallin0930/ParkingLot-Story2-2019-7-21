package com.example.demo.repository;

import com.example.demo.modle.ParkingLotOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotOrderRepository extends JpaRepository<ParkingLotOrder,Long> {
}
