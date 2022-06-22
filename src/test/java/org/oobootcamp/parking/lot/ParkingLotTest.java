package org.oobootcamp.parking.lot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parking.lot.exception.FullyParkedException;
import org.oobootcamp.parking.lot.exception.InvalidTicketException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    @Test
    void should_parking_successful_and_return_a_ticket_when_park_a_car_given_an_empty_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();

        Ticket ticket = parkingLot.park(car);

        // 1. how do we verify this case when there is nothing
        // 2. should we modify test after some feature is done, then we may need to modify production code as well?
        assertThat(ticket).isNotNull();
    }

    @Test
    void should_return_a_car_when_pick_up_a_car_given_a_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Ticket ticket = parkingLot.park(car1);

        Car car2 = parkingLot.pickUp(ticket);

        assertThat(car1).isSameAs(car2);
    }

    @Test
    void should_show_fully_parked_tip_when_parking_a_car_given_non_empty_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingLot.park(car1);

        assertThrows(FullyParkedException.class, () -> parkingLot.park(car2));
    }

    @Test
    void should_show_invalid_ticket_tip_when_pick_up_a_car_given_a_used_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Ticket ticket = parkingLot.park(car1);
        parkingLot.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> parkingLot.pickUp(ticket));
    }

    @Test
    void should_show_invalid_ticket_tip_when_pick_up_a_car_given_a_fake_ticket() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        parkingLot.park(car1);

        assertThrows(InvalidTicketException.class, () -> parkingLot.pickUp(new Ticket()));
    }
}
