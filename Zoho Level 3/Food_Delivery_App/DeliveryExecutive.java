package Food_Delivery_App;

import java.util.ArrayList;
import java.util.List;

public class DeliveryExecutive {
    String id;
    int deliveryCharges = 0;
    int allowance = 0; // Start with 0
    int tripCount = 0; // Start with 0 trips
    List<Booking> bookings = new ArrayList<>();

    public DeliveryExecutive(String id) {
        this.id = id;
    }

    public void addBooking(Booking booking, int deliveryCharge) {
        this.bookings.add(booking);
        this.deliveryCharges += deliveryCharge;
    }

    public void addAllowance() {
       
            this.allowance += 10; // Add allowance of Rs. 10 for the trip
        
    }

    public void incrementTripCount() {
        this.tripCount++; // Increment the trip count
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public String getId() {
        return id;
    }

    public int getTotalEarned() {
        return this.deliveryCharges + this.allowance;
    }

    @Override
    public String toString() {
        return id + " - Earned: " + getTotalEarned() + " (Delivery: " + deliveryCharges + ", Allowance: " + allowance + ", Trips: " + tripCount + ")";
    }
}
