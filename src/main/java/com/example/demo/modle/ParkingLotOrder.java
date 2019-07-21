package com.example.demo.modle;

import javax.persistence.*;

@Entity
public class ParkingLotOrder {
    @Id
    @GeneratedValue
    private long orderId;

    private long enterTime;
    private long leaveTime;
    private String status;
    private long carId;

    @ManyToOne(cascade= CascadeType.ALL)
    private ParkingLot parkinglot;

    public ParkingLotOrder() {
    }

    public ParkingLotOrder(long enterTime, String status, long carId) {
        this.enterTime = enterTime;
        this.status = status;
        this.carId = carId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(long enterTime) {
        this.enterTime = enterTime;
    }

    public long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public ParkingLot getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(ParkingLot parkinglot) {
        this.parkinglot = parkinglot;
    }
}
