package org.oobootcamp.parking.lot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parking.lot.exception.FullyParkedException;
import org.oobootcamp.parking.lot.exception.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingTest {
    @Test
    void should_parking_successful_and_return_a_ticket_when_park_a_car_given_an_empty_parking_lot() {
        Parking parking = new Parking(1);
        Car car = new Car();

        Ticket ticket = parking.park(car);

        // 1. how do we verify this case when there is nothing
        // 2. should we modify test after some feature is done, then we may need to modify production code as well?
        assertThat(ticket).isNotNull();
    }

    @Test
    void should_return_a_car_when_pick_up_a_car_given_a_valid_ticket() {
        Parking parking = new Parking(1);
        Car car1 = new Car();
        Ticket ticket = parking.park(car1);

        Car car2 = parking.pickUp(ticket);

        assertThat(car1).isSameAs(car2);
    }

    @Test
    void should_show_tip_when_parking_a_car_given_non_empty_parking_lot() {
        Parking parking = new Parking(0);
        Car car = new Car();

        assertThrows(FullyParkedException.class, () -> parking.park(car));
    }

    @Test
    void should_show_tip_when_parking_2_car_given_1_parking_lot() {
        Parking parking = new Parking(1);
        Car car1 = new Car();
        Car car2 = new Car();
        parking.park(car1);

        assertThrows(FullyParkedException.class, () -> parking.park(car2));
    }

    @Test
    void should_return_ticket_when_parking_a_car_successful() {
        Parking parking = new Parking(1);
        Car car1 = new Car();

        Ticket ticket = parking.park(car1);

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_show_tip_when_pick_up_a_car_given_a_ticket_twice() {
        Parking parking = new Parking(1);
        Car car1 = new Car();
        Ticket ticket = parking.park(car1);
        parking.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> parking.pickUp(ticket));
    }

    @Test
    void should_show_tip_when_pick_up_a_car_given_a_invalid_ticket() {
        Parking parking = new Parking(1);
        Car car1 = new Car();
        parking.park(car1);

        assertThrows(InvalidTicketException.class, () -> parking.pickUp(new Ticket()));
    }
}
