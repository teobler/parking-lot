package org.oobootcamp.parking.lot;

import org.junit.jupiter.api.Test;
import org.oobootcamp.parking.lot.exception.FullyParkedException;
import org.oobootcamp.parking.lot.exception.InvalidTicketException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyLotTest {
    @Test
    void should_park_a_car_successful_when_park_a_car_by_graduate_parking_boy_given_one_parking() {
        Car car = new Car();
        List<ParkingLot> parkingLotList = List.of(new ParkingLot(1));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);

        Ticket ticket = graduateParkingBoy.park(car);

        assertThat(ticket).isNotNull();
    }

    @Test
    void should_park_a_car_successful_in_the_first_paring_when_park_a_car_by_graduate_parking_boy_given_two_parking() {
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = List.of(parkingLot1, parkingLot2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);

        Ticket ticket = graduateParkingBoy.park(car);

        Car pickedUpCar = parkingLot1.pickUp(ticket);
        assertThat(pickedUpCar).isSameAs(car);
    }

    @Test
    void should_park_a_car_successful_in_the_second_paring_when_park_a_car_by_graduate_parking_boy_given_the_first_parking_is_full() {
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot1.park(car1);
        List<ParkingLot> parkingLotList = List.of(parkingLot1, parkingLot2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);

        Ticket ticket = graduateParkingBoy.park(car2);

        Car pickedUpCar = parkingLot2.pickUp(ticket);
        assertThat(pickedUpCar).isSameAs(car2);
    }

    @Test
    void should_show_tip_parking_is_full_when_park_a_car_by_graduate_parking_boy_given_one_non_available_parking_lot() {
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(car1);
        List<ParkingLot> parkingLotList = List.of(parkingLot1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);

        assertThrows(FullyParkedException.class, () -> graduateParkingBoy.park(car2));
    }

    @Test
    void should_show_tip_parking_is_full_when_park_a_car_by_graduate_parking_boy_given_two_non_available_parking_lots() {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot1.park(car1);
        parkingLot2.park(car2);
        List<ParkingLot> parkingLotList = List.of(parkingLot1, parkingLot2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);

        assertThrows(FullyParkedException.class, () -> graduateParkingBoy.park(car3));
    }

    @Test
    void should_pick_up_a_car_successful_when_pick_up_a_car_by_graduate_parking_boy_given_a_ticket_from_one_parking() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = List.of(parkingLot);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);
        Ticket ticket = graduateParkingBoy.park(car);

        Car pickedUpCar = graduateParkingBoy.pickUp(ticket);

        assertThat(pickedUpCar).isSameAs(car);
    }

    @Test
    void should_pick_up_a_car_successful_when_pick_up_a_car_by_graduate_parking_boy_given_a_ticket_from_the_first_parking() {
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = List.of(parkingLot1, parkingLot2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);
        Ticket ticket = parkingLot1.park(car);

        Car pickedUpCar = graduateParkingBoy.pickUp(ticket);

        assertThat(pickedUpCar).isEqualTo(car);
    }

    @Test
    void should_pick_up_a_car_successful_when_pick_up_a_car_by_graduate_parking_boy_given_a_ticket_from_the_second_parking() {
        Car car = new Car();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> parkingLotList = List.of(parkingLot1, parkingLot2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);
        Ticket ticket = parkingLot2.park(car);

        Car pickedUpCar = graduateParkingBoy.pickUp(ticket);

        assertThat(pickedUpCar).isEqualTo(car);
    }

    @Test
    void should_show_tip_invalid_ticket_when_pick_up_a_car_by_graduate_parking_boy_given_a_used_ticket() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = List.of(parkingLot);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);
        Ticket ticket = graduateParkingBoy.park(car);
        graduateParkingBoy.pickUp(ticket);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUp(ticket));
    }

    @Test
    void should_show_tip_invalid_ticket_when_pick_up_a_car_by_graduate_parking_boy_given_a_fake_ticket() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = List.of(parkingLot);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkingLotList);
        graduateParkingBoy.park(car);

        assertThrows(InvalidTicketException.class, () -> graduateParkingBoy.pickUp(new Ticket()));
    }
}
