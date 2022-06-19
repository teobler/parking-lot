package org.oobootcamp.parking.lot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Parking {
    private int parkingLotNumber;

    private Map<String, Car> parkingLots = new HashMap();

    public Parking(int parkingLotNumber) {
        this.parkingLotNumber = parkingLotNumber;
    }

    public int getParkingLotNumber() {
        return this.parkingLotNumber;
    }

    public String park(Car car) {
        if (this.parkingLotNumber == 0) {
            System.out.print("停车场车位已满");
            return null;
        }
        this.parkingLotNumber -= 1;
        String ticket = String.valueOf(UUID.randomUUID());
        this.parkingLots.put(ticket, car);
        return ticket;
    }

    public Car pickUp(String ticket) {
        Car car = this.parkingLots.get(ticket);
        if (car == null) {
            System.out.print("无效票");
            return null;
        }
        this.parkingLots.remove(ticket);
        return car;
    }
}
