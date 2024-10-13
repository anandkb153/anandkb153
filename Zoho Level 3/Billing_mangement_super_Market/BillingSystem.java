package Billing_mangement_super_Market;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class BillingSystem {
    private static Admin admin = new Admin("admin", "admin123");
    private static List<Customer> customers = new ArrayList<>();
    private static List<String> salesReport = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        customers.add(new Customer("customer1", "cust123"));

        while (true) {
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Register New Customer");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    handleCustomerLogin();
                    break;
                case 3:
                    registerNewCustomer();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void handleAdminLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (admin.getUsername().equals(username) && admin.authenticate(password)) {
            adminMenu();
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private static void handleCustomerLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.authenticate(password)) {
                System.out.println("Welcome " + customer.getUsername());
                System.out.println("-----------------------------------");
                customerMenu(customer);
                return;
            }
        }
        System.out.println("Invalid credentials!");
    }

    private static void registerNewCustomer() {
        System.out.print("Enter new customer username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new customer password: ");
        String password = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                System.out.println("Username already exists. Please choose a different username.");
                return;
            }
        }

        customers.add(new Customer(username, password));
        System.out.println("Customer registered successfully!");
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. View Sales Report");
            System.out.println("5. Set Offer");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    admin.viewProducts();
                    break;
                case 4:
                    viewSalesReport();
                    break;
                case 5:
                    setOffer();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Product Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline
        admin.addProduct(new Product(id, name, price, quantity));
        
    }

    private static void removeProduct() {
        System.out.print("Enter Product ID to remove: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        admin.removeProduct(productId);
        System.out.println("Product removed successfully!");
    }

    private static void setOffer() {
        System.out.print("Enter the minimum amount for the offer: ");
        double eligibleAmount = scanner.nextDouble();
        System.out.print("Enter the offer percentage: ");
        double offerPercentage = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        admin.setOffer(eligibleAmount, offerPercentage);
    }

    private static void customerMenu(Customer customer) {
        while (true) {
            System.out.println("Customer Menu:");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Checkout");
            System.out.println("4. View Transaction History");
            System.out.println("5. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    customer.viewProducts(admin.getProductList());
                    break;
                case 2:
                    addToCart(customer);
                    break;
                case 3:
                    checkout(customer);
                    break;
                case 4:
                    customer.viewTransactionHistory();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    private static void addToCart(Customer customer) {
        System.out.print("Enter Product ID to add to cart: ");
        int productId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Product selectedProduct = null;

        for (Product product : admin.getProductList()) {
            if (product.getId() == productId) {
                selectedProduct = product;
                break;
            }
        }

        if (selectedProduct != null) {
            if (quantity <= selectedProduct.getQuantity()) {
                customer.addToCart(selectedProduct, quantity);
                selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity); // Decrease quantity
                System.out.println("Product added to cart successfully!");
            } else {
                System.out.println("Requested quantity exceeds available stock!");
            }
        } else {
            System.out.println("Product not found!");
        }
    }

    private static void checkout(Customer customer) {
        if (customer.isCartEmpty()) {
            System.out.println("Cart is empty. Please add items to the cart before checkout.");
            return;
        }

        double totalAmount = customer.checkout();
        double discountedAmount = admin.applyOffer(totalAmount);
        customer.displayCart();
        System.out.printf("Total amount after discount: %.2f%n", discountedAmount);
        System.out.print("Confirm purchase (yes/no): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            String transactionDetails = customer.confirmPurchase(discountedAmount);
            recordTransaction(transactionDetails);
            System.out.println("Purchase confirmed. Thank you for shopping with us!");
        } else {
            System.out.println("Purchase canceled.");
        }
    }

    private static void viewSalesReport() {
        if (salesReport.isEmpty()) {
            System.out.println("No sales transactions found.");
            return;
        }

        System.out.println("Sales Report:");
        for (String report : salesReport) {
            System.out.println(report);
            System.out.println("-----");
        }
    }

    public static void recordTransaction(String transactionDetails) {
        salesReport.add(transactionDetails);
    }
}
