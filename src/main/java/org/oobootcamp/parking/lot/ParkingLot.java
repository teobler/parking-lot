package org.oobootcamp.parking.lot;

import org.oobootcamp.parking.lot.exception.FullyParkedException;
import org.oobootcamp.parking.lot.exception.InvalidTicketException;

import java.util.HashMap;

public class ParkingLot {
    private final int capacity;

    private final HashMap<Ticket, Car> parkedCars = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        if (!this.hasEmptyLot()) {
            throw new FullyParkedException();
        }

        Ticket ticket = new Ticket();
        this.parkedCars.put(ticket, car);

        return ticket;
    }

    public Car pickUp(Ticket ticket) {
        Car car = this.parkedCars.get(ticket);

        if (car == null) {
            throw new InvalidTicketException();
        }

        this.parkedCars.remove(ticket);
        return car;
    }

    public Boolean hasEmptyLot() {
        return this.capacity > this.parkedCars.size();
    }

    public Boolean hasCar(Ticket ticket) {
        return this.parkedCars.containsKey(ticket);
    }
}
