import java.util.*;

class Flight {
    private String flightName;
    private int availableSeats;
    private int ticketPrice;
    private Map<String, Passenger> bookings;
    private int bookingCounter;

    public Flight(String flightName) {
        this.flightName = flightName;
        this.availableSeats = 50;
        this.ticketPrice = 5000;
        this.bookings = new HashMap<>();
        this.bookingCounter = 0;
    }

    public String bookTickets(String passengerName, int age, int seats) {
        if (seats <= availableSeats) {
            bookingCounter++;
            String bookingId = "T" + bookingCounter;
            Passenger passenger = new Passenger(bookingId, passengerName, age, seats);
            bookings.put(bookingId, passenger);
            availableSeats -= seats;
            ticketPrice += 200 * seats;
            return bookingId;
        } else {
            System.out.println("Booking failed: Not enough seats available.");
            return null;
        }
    }

    public boolean cancelBooking(String bookingId) {
        Passenger passenger = bookings.get(bookingId);
        if (passenger != null) {
            int seats = passenger.getSeatsBooked();
            availableSeats += seats;
            ticketPrice -= 200 * seats;
            bookings.remove(bookingId);
            System.out.println("Booking canceled successfully. Refund issued for " + seats + " seats.");
            return true;
        } else {
            System.out.println("Cancellation failed: Booking ID not found.");
            return false;
        }
    }

    public void displayDetails() {
        System.out.println("Flight: " + flightName);
        System.out.println("Available Seats: " + availableSeats);
        System.out.println("Current Ticket Price: ₹" + ticketPrice);
    }

    public void printDetails() {
        System.out.println("Flight: " + flightName);
        System.out.println("Available Seats: " + availableSeats);
        System.out.println("Current Ticket Price: ₹" + ticketPrice);
        System.out.println("Passengers:");
        for (Passenger passenger : bookings.values()) {
            System.out.println(passenger);
        }
    }
}
