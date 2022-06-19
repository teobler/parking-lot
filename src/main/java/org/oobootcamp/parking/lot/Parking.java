package org.oobootcamp.parking.lot;

import java.util.HashMap;
import java.util.UUID;

public class Parking {
    private int parkingLotNumber;

    private final HashMap<Ticket, Car> parkingLots = new HashMap<>();

    public Parking(int parkingLotNumber) {
        this.parkingLotNumber = parkingLotNumber;
    }

    public int getParkingLotNumber() {
        return this.parkingLotNumber;
    }

    public Ticket park(Car car) {
        if (this.parkingLotNumber == 0) {
            System.out.print("停车场车位已满");
            return null;
        }
        this.parkingLotNumber -= 1;
        Ticket ticket = new Ticket(UUID.randomUUID().toString());
        this.parkingLots.put(ticket, car);
        return ticket;
    }

    public Object pickUp(Ticket ticket) {
        Object car = this.parkingLots.get(ticket);
        if (car == null) {
            System.out.print("无效票");
            return null;
        }
        this.parkingLots.remove(ticket);
        return car;
    }
}
