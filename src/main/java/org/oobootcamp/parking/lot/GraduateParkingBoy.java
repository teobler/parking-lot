package org.oobootcamp.parking.lot;

import org.oobootcamp.parking.lot.exception.FullyParkedException;
import org.oobootcamp.parking.lot.exception.InvalidTicketException;

import java.util.List;

public class GraduateParkingBoy {
    private final List<Parking> parkingList;

    public GraduateParkingBoy(List<Parking> parkingList) {
        this.parkingList = parkingList;
    }

    public Ticket park(Car car) {
        return this.parkingList.stream()
                .filter(parking -> parking.getParkingLotNumber() > 0)
                .findFirst()
                .orElseThrow(FullyParkedException::new)
                .park(car);
    }

    public Car pickUp(Ticket ticket) {
        return this.parkingList.stream()
                .filter(parking -> parking.hasCar(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }
}
