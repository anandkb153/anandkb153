package Interview_Programs;

import java.util.Scanner;

public class Driver_Class {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TrainTicketBooking ticketBooking = new TrainTicketBooking();
        
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Book Tickets");
            System.out.println("2. Cancel Tickets");
            System.out.println("3. Print Chart");
             System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter source station (A/B/C/D/E): ");
                    String from = scanner.nextLine().toUpperCase();
                    
                    System.out.print("Enter destination station (A/B/C/D/E): ");
                    String to = scanner.nextLine().toUpperCase();
                    
                    System.out.print("Enter number of seats to book: ");
                    int seats = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    ticketBooking.book(from, to, seats);
                    break;
                    
                case 2:
                    System.out.print("Enter PNR number to cancel: ");
                    int pnr = scanner.nextInt();
                    
                    System.out.print("Enter number of seats to cancel: ");
                    int seatsToCancel = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    
                    ticketBooking.cancel(pnr, seatsToCancel);
                    break;
                    
                case 3:
                	ticketBooking.printChart();
                    break;
                    
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
}
