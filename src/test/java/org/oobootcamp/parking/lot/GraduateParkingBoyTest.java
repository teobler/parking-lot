package org.oobootcamp.parking.lot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parking.lot.exception.FullyParkedException;
import org.oobootcamp.parking.lot.exception.InvalidTicketException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraduateParkingBoyTest {
    @Test
    void should_park_a_car_successful_when_park_a_car_by_graduate_parking_boy_given_one_parking() {
        Car car = new Car();
        List<Parking> parkingList = List.of(new Parking(1));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingList);

        Ticket ticket = graduateParkingBoy.park(car);

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_park_a_car_successful_in_the_first_paring_when_park_a_car_by_graduate_parking_boy_given_two_parking() {
        Car car = new Car();
        Parking parking1 = new Parking(1);
        Parking parking2 = new Parking(1);
        List<Parking> parkingList = List.of(parking1, parking2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingList);

        Ticket ticket = graduateParkingBoy.park(car);

        Car pickedUpCar = parking1.pickUp(ticket);
        assertThat(pickedUpCar).isSameAs(car);
    }

    @Test
    void should_park_a_car_successful_in_the_second_paring_when_park_a_car_by_graduate_parking_boy_given_the_first_parking_is_full() {
        Car car1 = new Car();
        Car car2 = new Car();
        Parking parking1 = new Parking(1);
        Parking parking2 = new Parking(1);
        parking1.park(car1);
        List<Parking> parkingList = List.of(parking1, parking2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingList);

        Ticket ticket = graduateParkingBoy.park(car2);

        Car pickedUpCar = parking2.pickUp(ticket);
        assertThat(pickedUpCar).isSameAs(car2);
    }

    @Test
    void should_show_tip_parking_is_full_when_park_a_car_by_graduate_parking_boy_given_non_available_parking_lot() {
        Car car1 = new Car();
        Car car2 = new Car();
        Parking parking1 = new Parking(1);
        parking1.park(car1);
        List<Parking> parkingList = List.of(parking1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingList);

        assertThrows(FullyParkedException.class, () -> graduateParkingBoy.park(car2));
    }

    @Test
    void should_pick_up_a_car_successful_when_pick_up_a_car_by_graduate_parking_boy_given_a_ticket_from_parking() {
        Car car1 = new Car();
        Car car2 = new Car();
        Parking parking1 = new Parking(1);
        Parking parking2 = new Parking(1);
        parking1.park(car1);
        List<Parking> parkingList = List.of(parking1, parking2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingList);
        Ticket ticket = graduateParkingBoy.park(car2);

        Car actual = graduateParkingBoy.pickUp(ticket);

        assertThat(actual).isEqualTo(car2);
    }

    @Test
    void should_show_tip_invalid_ticket_when_pick_up_a_car_by_graduate_parking_boy_given_a_used_ticket() {
        Car car1 = new Car();
        Parking parking1 = new Parking(1);
        List<Parking> parkingList = List.of(parking1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingList);
        Ticket ticket = graduateParkingBoy.park(car1);
        graduateParkingBoy.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUp(ticket));
    }

    @Test
    void should_show_tip_invalid_ticket_when_pick_up_a_car_by_graduate_parking_boy_given_a_fake_ticket() {
        Car car1 = new Car();
        Parking parking1 = new Parking(1);
        List<Parking> parkingList = List.of(parking1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingList);
        graduateParkingBoy.park(car1);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUp(new Ticket()));
    }
}
