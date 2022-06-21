package org.oobootcamp.parking.lot;

import org.oobootcamp.parking.lot.exception.FullyParkedException;
import org.oobootcamp.parking.lot.exception.InvalidTicketException;

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
            throw new FullyParkedException();
        }
        this.parkingLotNumber -= 1;
        Ticket ticket = new Ticket();
        this.parkingLots.put(ticket, car);
        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        Car car = this.parkingLots.get(ticket);
        if (car == null) {
            throw new InvalidTicketException();
        }
        this.parkingLots.remove(ticket);
        return car;
    }

    public Boolean hasCar(Ticket ticket) {
        return this.parkingLots.containsKey(ticket);
    }
}
