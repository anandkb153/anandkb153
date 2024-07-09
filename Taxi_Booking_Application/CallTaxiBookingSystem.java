package Taxi_Booking_Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CallTaxiBookingSystem {
    List<Taxi> taxis;
    int bookingIdCounter;

    public CallTaxiBookingSystem(int numberOfTaxis) {
        taxis = new ArrayList<>();
        bookingIdCounter = 1;
        for (int i = 1; i <= numberOfTaxis; i++) {
            taxis.add(new Taxi(i));
        }
    }

    public void bookTaxi(int customerId, char pickupPoint, char dropPoint, int pickupTime) {
        Taxi availableTaxi = findAvailableTaxi(pickupPoint, pickupTime);
        if (availableTaxi == null) {
            System.out.println("Booking rejected. No available taxi at this time.");
            return;
        }

        int distance = Math.abs(dropPoint - pickupPoint) * 15;
        int amount = calculateFare(distance);
        int dropTime = pickupTime + (distance / 15);
        Booking booking = new Booking(bookingIdCounter++, customerId, pickupPoint, dropPoint, pickupTime, dropTime, amount);
        availableTaxi.addBooking(booking);

        System.out.println("Taxi can be allotted.");
        System.out.println("Taxi-" + availableTaxi.id + " is allotted.");
    }

    private Taxi findAvailableTaxi(char pickupPoint, int pickupTime) {
        Taxi nearestTaxi = null;
        int nearestDistance = Integer.MAX_VALUE;

        for (Taxi taxi : taxis) {
            if (isTaxiAvailable(taxi, pickupTime)) {
                int distance = Math.abs(taxi.currentLocation - pickupPoint);
                if (distance < nearestDistance || (distance == nearestDistance && 
                		taxi.totalEarnings < (nearestTaxi != null ? nearestTaxi.totalEarnings : Integer.MAX_VALUE))) {
                    nearestDistance = distance;
                    nearestTaxi = taxi;
                }
            }
        }

        return nearestTaxi;
    }

    private boolean isTaxiAvailable(Taxi taxi, int pickupTime) {
        if (taxi.bookings.isEmpty()) return true;
        Booking lastBooking = taxi.bookings.get(taxi.bookings.size() - 1);
        return lastBooking.dropTime <= pickupTime;
    }

    private int calculateFare(int distance) {
        if (distance <= 5) return 100;
        return 100 + (distance - 5) * 10;
    }

    public void displayTaxiDetails() {
        for (Taxi taxi : taxis) {
            System.out.println("Taxi-" + taxi.id + " Total Earnings: Rs. " + taxi.totalEarnings);
            if (!taxi.bookings.isEmpty()) 
                for (Booking booking : taxi.bookings) {
                    System.out.println("BookingID =>"+booking.bookingId + "\n" 
                +"CustomerID =>"+ booking.customerId + "\n" +"From =>"+
                booking.pickupPoint + "\n" +"To => "+ booking.dropPoint + "\n" 
                +"PickupTime =>"+ booking.pickupTime + "\n" +"DropTime =>"+ booking.dropTime + "\n" +"Amount =>"+ booking.amount);
                   System.out.println("--------------------------------------------------------------------");
                }
            }
            System.out.println();
        }
   

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CallTaxiBookingSystem system = new CallTaxiBookingSystem(4);

        while (true) {
            System.out.println("1. Book a Taxi");
            System.out.println("2. Display Taxi Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();
                    System.out.print("Enter Pickup Point (A-F): ");
                    char pickupPoint = scanner.next().charAt(0);
                    System.out.print("Enter Drop Point (A-F): ");
                    char dropPoint = scanner.next().charAt(0);
                    System.out.print("Enter Pickup Time (hours): ");
                    int pickupTime = scanner.nextInt();

                    system.bookTaxi(customerId, pickupPoint, dropPoint, pickupTime);
                    break;

                case 2:
                    system.displayTaxiDetails();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

