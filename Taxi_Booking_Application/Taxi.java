package Taxi_Booking_Application;

import java.util.ArrayList;
import java.util.List;

public class Taxi {
    int id;
    char currentLocation;
    int totalEarnings;
    List<Booking> bookings;

    public Taxi(int id) {
        this.id = id;
        this.currentLocation = 'A';
        this.totalEarnings = 0;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        totalEarnings += booking.amount;
        currentLocation = booking.dropPoint;
    }
}

