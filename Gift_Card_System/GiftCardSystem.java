package Gift_Card_System;

import java.util.*;

public class GiftCardSystem {
    private static List<Customer> customers = new ArrayList<>();
    private static void initializeCustomers() {
        // Initial customer details
        customers.add(new Customer(11, "110110", "Kumar", 10000, "ZohoMail"));
        customers.add(new Customer(22, "220220", "Madhu", 20000, "Apple"));//Bqqmf
        customers.add(new Customer(33, "330330", "Robin", 30000, "AcEg123"));
    }
    public static void main(String[] args) {
        initializeCustomers();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Account Login");
            System.out.println("2. Purchase");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // consume the newline

            switch (option) {
                case 1:
                    accountLogin(scanner);
                    break;
                case 2:
                    purchase(scanner);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

 

    private static void accountLogin(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Encrypted Password: ");
        String password = scanner.nextLine();

        Customer customer = findCustomerById(customerId);
        if (customer != null && customer.verifyPassword(password)) {
        	System.out.println("Welcome   "+customer.getName());
            accountOptions(scanner, customer);
        } else {
            System.out.println("Invalid ID or Password.");
        }
    }

    private static Customer findCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    private static void accountOptions(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("\n--- Account Options ---");
            System.out.println("1. Create a new Gift Card");
            System.out.println("2. Top-up the existing Card");
            System.out.println("3. Show Gift Card transaction history");
            System.out.println("4. Block the existing Card");
            System.out.println("5. Show Account Balance");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (option) {
                case 1:
                    customer.createNewGiftCard(scanner);
                    break;
                case 2:
                    customer.topUpGiftCard(scanner);
                    break;
                case 3:
                    customer.showTransactionHistory();
                    break;
                case 4:
                    customer.blockGiftCard();
                    break;
                case 5:
                   System.out.println("Current Balance = "+customer.getBalance());
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void purchase(Scanner scanner) {
        System.out.print("Enter your bill amount: ");
        int amount = scanner.nextInt();
        scanner.nextLine(); // consume the newline
        System.out.print("Enter your Card Number: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        for (Customer customer : customers) {
            if (customer.validateGiftCard(cardNumber, pin, amount)) {
                System.out.println("Purchase successful. Remaining balance: " + customer.getGiftCardBalance());
                return;
            }
        }
        System.out.println("Invalid card or insufficient balance.");
    }
}
