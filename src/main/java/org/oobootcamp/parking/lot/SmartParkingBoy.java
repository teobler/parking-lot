package org.oobootcamp.parking.lot;

import java.util.List;

public class SmartParkingBoy {

    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = this.parkingLots.stream()
                .max((parkingLot1, parkingLot2) ->
                        parkingLot1.getEmptySpots().compareTo(parkingLot2.getEmptySpots()))
                .get();

        return parkingLot.park(car);
    }
}
