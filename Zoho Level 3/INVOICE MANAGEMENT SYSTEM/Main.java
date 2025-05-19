import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InvoiceService service = new InvoiceService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Invoice Management System ---");
            System.out.println("1. Add a customer");
            System.out.println("2. Add an invoice");
            System.out.println("3. Add items to an invoice");
            System.out.println("4. List all customers");
            System.out.println("5. List all invoices");
            System.out.println("6. List all invoices of a customer");
            System.out.println("7. Display the full details of an invoice");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> addInvoice();
                case 3 -> addItemsToInvoice();
                case 4 -> service.listAllCustomers();
                case 5 -> service.listAllInvoices();
                case 6 -> listInvoicesOfCustomer();
                case 7 -> displayInvoiceDetails();
                case 8 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCustomer() {
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        service.addCustomer(id, name);
    }

    private static void addInvoice() {
        System.out.print("Enter invoice ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        service.addInvoice(id, customerId);
    }

    private static void addItemsToInvoice() {
        System.out.print("Enter invoice ID: ");
        String invoiceId = scanner.nextLine();
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        service.addItemToInvoice(invoiceId, itemName, quantity, price);
    }

    private static void listInvoicesOfCustomer() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        service.listInvoicesOfCustomer(customerId);
    }

    private static void displayInvoiceDetails() {
        System.out.print("Enter invoice ID: ");
        String invoiceId = scanner.nextLine();
        service.displayInvoiceDetails(invoiceId);
    }
}
