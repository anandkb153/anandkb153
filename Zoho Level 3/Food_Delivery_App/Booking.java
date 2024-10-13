package Food_Delivery_App;

public class Booking {
    int customerId;
    String restaurant;
    String destination;
    String time;

    public Booking(int customerId, String restaurant, String destination, String time) {
        this.customerId = customerId;
        this.restaurant = restaurant;
        this.destination = destination;
        this.time = time;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public String getDestination() {
        return destination;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Booking [Customer ID=" + customerId + ", Restaurant=" + restaurant + ", Destination=" + destination + ", Time=" + time + "]";
    }
}
