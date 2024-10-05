package Lift_Management_System;

import java.util.Scanner;

public class LiftManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LiftSystem liftSystem = new LiftSystem();
        int choice;

        do {
            System.out.println("\n--- Lift Management System ---");
            System.out.println("1. Display Lift Positions");
            System.out.println("2. Assign Lift");
            System.out.println("3. Set Lift Under Maintenance");
            System.out.println("4. Remove Lift from Maintenance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    liftSystem.displayLiftPositions();
                    break;
                case 2:
                    System.out.print("Enter user's source floor: ");
                    int sourceFloor = scanner.nextInt();
                    System.out.print("Enter user's destination floor: ");
                    int destinationFloor = scanner.nextInt();
                    liftSystem.assignLift(sourceFloor, destinationFloor);
                    break;
                case 3:
                    System.out.print("Enter lift name to set under maintenance: ");
                    String liftUnderMaintenance = scanner.next();
                    liftSystem.setLiftUnderMaintenance(liftUnderMaintenance);
                    break;
                case 4:
                    System.out.print("Enter lift name to remove from maintenance: ");
                    String liftAvailable = scanner.next();
                    liftSystem.removeLiftFromMaintenance(liftAvailable);
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
