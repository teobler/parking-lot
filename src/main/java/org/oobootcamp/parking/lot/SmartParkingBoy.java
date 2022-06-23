package org.oobootcamp.parking.lot;

import org.oobootcamp.parking.lot.exception.InvalidTicketException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy {

    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return this.parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getEmptySpots))
                .get()
                .park(car);
    }

    public Car pickUp(Ticket ticket) {
        return this.parkingLots.stream()
                .filter(parkingLot -> parkingLot.hasCar(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }
}
