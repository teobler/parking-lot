package org.oobootcamp.parking.lot;

import org.oobootcamp.parking.lot.exception.FullyParkedException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends GraduateParkingBoy {

    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return this.parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getEmptySpots))
                .orElseThrow(FullyParkedException::new)
                .park(car);
    }
}
