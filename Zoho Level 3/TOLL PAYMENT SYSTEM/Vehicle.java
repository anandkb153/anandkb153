import java.util.*;

class Vehicle {
    String vehicleNumber;
    String vehicleType;
    boolean isVIP;
    List<Journey> journeys;

    public Vehicle(String vehicleNumber, String vehicleType, boolean isVIP) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.isVIP = isVIP;
        this.journeys = new ArrayList<>();
    }

    public void addJourney(Journey journey) {
        journeys.add(journey);
    }

    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Type: " + vehicleType + ", VIP: " + isVIP);
        for (Journey journey : journeys) {
            System.out.println("Journey: " + journey.startPoint + " -> " + journey.endPoint);
            //tollsPassed list we will create in Journey class
            System.out.println("Tolls Passed: " + journey.tollsPassed);
            //amountPaid also we will create in Journey class
            System.out.println("Amount Paid: " + journey.amountPaid);
        }
        int totalPaid = 0;
        for (Journey j : journeys) {
            totalPaid += j.amountPaid;
        }
        System.out.println("Total Amount Paid: " + totalPaid);
    }
}
