package org.oobootcamp.parking.lot;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class SmartParkingBoyTest {
    @Test
    void should_parking_a_car_successful_and_get_ticket_when_park_a_car_given_a_parking_lot_with_one_empty_spot() {
        ParkingLot parkingLot = new ParkingLot(1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot));
        Car car = new Car();

        Ticket ticket = smartParkingBoy.park(car);

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_parking_a_car_to_first_parking_lot_and_get_ticket_when_park_a_car_given_first_parking_lot_with_more_empty_spots() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car car = new Car();

        Ticket ticket = smartParkingBoy.park(car);

        assertThat(firstParkingLot.hasCar(ticket)).isTrue();
    }

    @Test
    void should_parking_a_car_to_second_parking_lot_and_get_ticket_when_park_a_car_given_second_parking_lot_with_more_empty_spots() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car car = new Car();

        Ticket ticket = smartParkingBoy.park(car);

        assertThat(secondParkingLot.hasCar(ticket)).isTrue();
    }

    @Test
    void should_parking_a_car_to_frst_parking_lot_and_get_ticket_when_park_a_car_given_two_parking_lot_with_same_empty_spots() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Car car = new Car();

        Ticket ticket = smartParkingBoy.park(car);

        assertThat(firstParkingLot.hasCar(ticket)).isTrue();
    }
}
