package org.oobootcamp.parking.lot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingTest {

    @Test
    void should_return_specific_parking_when_create_parking_given_specific_parking_lot_number() {
        Parking parking = new Parking(1);

        assertThat(parking.getParkingLotNumber()).isEqualTo(1);
    }

    // this test is useless?
    // should we modify test after some feature is done
    @Test
    void should_parking_successful_when_parking_a_car_given_1_empty_parking_lot() {
        Parking parking = new Parking(1);
        Car car = new Car("uuid");

        assertDoesNotThrow(() -> parking.park(car));
    }

    @Test
    void should_show_tip_when_parking_a_car_given_non_empty_parking_lot() {
        Parking parking = new Parking(0);
        Car car = new Car("uuid");

        Throwable exception = assertThrows(RuntimeException.class, () -> parking.park(car));

        assertThat(exception.getMessage()).isEqualTo("停车场车位已满");
    }

    @Test
    void should_show_tip_when_parking_2_car_given_1_parking_lot() {
        Parking parking = new Parking(1);
        Car car1 = new Car("uuid1");
        Car car2 = new Car("uuid2");
        parking.park(car1);

        Throwable exception = assertThrows(RuntimeException.class, () -> parking.park(car2));

        assertThat(exception.getMessage()).isEqualTo("停车场车位已满");
    }

    @Test
    void should_return_ticket_when_parking_a_car_successful() {
        Parking parking = new Parking(1);
        Car car1 = new Car("uuid1");


        Ticket ticket = parking.park(car1);

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_return_a_car_when_pick_up_a_car_given_a_valid_ticket() {
        Parking parking = new Parking(1);
        Car car1 = new Car("uuid1");
        Ticket ticket = parking.park(car1);

        Object car2 = parking.pickUp(ticket);

        assertThat(car1).isEqualTo(car2);
    }


    @Test
    void should_show_tip_when_pick_up_a_car_given_a_ticket_twice() {
        Parking parking = new Parking(1);
        Car car1 = new Car("uuid1");
        Ticket ticket = parking.park(car1);
        parking.pickUp(ticket);

        Throwable exception = assertThrows(RuntimeException.class, () -> parking.pickUp(ticket));

        assertThat(exception.getMessage()).isEqualTo("无效票");
    }

    @Test
    void should_show_tip_when_pick_up_a_car_given_a_invalid_ticket() {
        Parking parking = new Parking(1);
        Car car1 = new Car("uuid1");
        parking.park(car1);

        Throwable exception = assertThrows(RuntimeException.class, () -> parking.pickUp(new Ticket("invalid")));

        assertThat(exception.getMessage()).isEqualTo("无效票");
    }
}
