package Taxi_Booking_Application;

public class Booking {
    int bookingId;
    int customerId;
    char pickupPoint;
    char dropPoint;
    int pickupTime;
    int dropTime;
    int amount;

    public Booking(int bookingId, int customerId, char pickupPoint, char dropPoint, int pickupTime, int dropTime, int amount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.pickupPoint = pickupPoint;
        this.dropPoint = dropPoint;
        this.pickupTime = pickupTime;
        this.dropTime = dropTime;
        this.amount = amount;
    }
}
