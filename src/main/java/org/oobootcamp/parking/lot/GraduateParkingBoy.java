package org.oobootcamp.parking.lot;

import org.oobootcamp.parking.lot.exception.FullyParkedException;
import org.oobootcamp.parking.lot.exception.InvalidTicketException;

import java.util.List;

public class GraduateParkingBoy {
    private final List<ParkingLot> parkingLotList;

    public GraduateParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public Ticket park(Car car) {
        return this.parkingLotList.stream()
                .filter(ParkingLot::hasEmptyLot)
                .findFirst()
                .orElseThrow(FullyParkedException::new)
                .park(car);
    }

    public Car pickUp(Ticket ticket) {
        return this.parkingLotList.stream()
                .filter(parkingLot -> parkingLot.hasCar(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pickUp(ticket);
    }
}
