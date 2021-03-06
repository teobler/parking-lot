package org.oobootcamp.parking.lot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parking.lot.exception.FullyParkedException;
import org.oobootcamp.parking.lot.exception.InvalidTicketException;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void should_show_fully_parked_tip_when_park_a_car_given_one_parking_lot_without_empty_spot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car firstCar = new Car();
        Car secondCar = new Car();
        parkingLot.park(firstCar);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot));

        assertThrows(FullyParkedException.class, () -> smartParkingBoy.park(secondCar));
    }

    @Test
    void should_show_fully_parked_tip_when_park_a_car_given_two_parking_lot_without_empty_spot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Car firstCar = new Car();
        Car secondCar = new Car();
        Car thirdCar = new Car();
        firstParkingLot.park(firstCar);
        secondParkingLot.park(secondCar);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));

        assertThrows(FullyParkedException.class, () -> smartParkingBoy.park(thirdCar));
    }

    @Test
    void should_get_a_car_when_pick_up_a_car_given_ticket_from_smart_parking_boy() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car firstCar = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot));
        Ticket ticket = parkingLot.park(firstCar);

        Car pickedUpCar = smartParkingBoy.pickUp(ticket);

        assertThat(pickedUpCar).isSameAs(firstCar);
    }

    @Test
    void should_get_a_car_from_first_parking_lot_when_pick_up_a_car_given_a_car_parked_in_first_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Ticket ticket = firstParkingLot.park(car);

        Car pickedUpCar = smartParkingBoy.pickUp(ticket);

        assertThat(pickedUpCar).isSameAs(car);
    }

    @Test
    void should_get_a_car_from_second_parking_lot_when_pick_up_a_car_given_a_car_parked_in_second_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(firstParkingLot, secondParkingLot));
        Ticket ticket = secondParkingLot.park(car);

        Car pickedUpCar = smartParkingBoy.pickUp(ticket);

        assertThat(pickedUpCar).isSameAs(car);
    }

    @Test
    void should_show_invalid_ticket_tip_when_pick_up_a_car_given_a_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot));
        Ticket ticket = parkingLot.park(car);
        parkingLot.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> smartParkingBoy.pickUp(ticket));
    }

    @Test
    void should_show_invalid_ticket_tip_when_pick_up_a_car_given_a_fake_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(List.of(parkingLot));
        parkingLot.park(car);

        assertThrows(InvalidTicketException.class, () -> smartParkingBoy.pickUp(new Ticket()));
    }
}
