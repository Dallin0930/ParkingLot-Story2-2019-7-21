package com.example.demo.modle;

import javax.persistence.*;
import java.util.List;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    private  Integer capacity;
    private String location;
    private int parkedCarNumber;

    @OneToMany(cascade=CascadeType.ALL)
    private List<ParkingLotOrder> parkingLotOrders;

    public ParkingLot() {
    }

    public ParkingLot(String name, Integer capacity, String location,int parkedCarNumber) {
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.parkedCarNumber=parkedCarNumber;
    }

    public String getName() {
        return name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId(long l) {
        return id;
    }

    public int getParkedCarNumber() {
        return parkedCarNumber;
    }

    public void setParkedCarNumber(int parkedCarNumber) {
        this.parkedCarNumber = parkedCarNumber;
    }

    public List<ParkingLotOrder> getParkingLotOrders() {
        return parkingLotOrders;
    }

    public void setParkingLotOrders(List<ParkingLotOrder> parkingLotOrders) {
        this.parkingLotOrders = parkingLotOrders;
    }
}
