package stockmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class StockManagementSystem {

    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<Stock> stocks = new ArrayList<>();
    static ArrayList<Sales> salesList = new ArrayList<>();
    static ArrayList<TransactionHistory> transactionHistoryList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int productCounter = 1;
    static int stockCounter = 1;
    static int saleCounter = 1;
    static int transactionCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStock Management System:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Stock");
            System.out.println("3. Restock Product");
            System.out.println("4. Make Sale");
            System.out.println("5. View Product List");
            System.out.println("6. View Stock List");
            System.out.println("7. View Transaction History");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateStock();
                    break;
                case 3:
                    restockProduct();
                    break;
                case 4:
                    makeSale();
                    break;
                case 5:
                    viewProductList();
                    break;
                case 6:
                    viewStockList();
                    break;
                case 7:
                    viewTransactionHistory();
                    break;
                case 8:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void addProduct() {
        scanner.nextLine(); // consume newline
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter supplier ID: ");
        int supplierId = scanner.nextInt();
        Supplier supplier = new Supplier(supplierId, "Supplier" + supplierId); // Simple supplier name

        Product product = new Product(productCounter++, name, price, supplier);
        products.add(product);
        System.out.println("Product added: " + product);
    }

    public static void updateStock() {
        System.out.print("Enter product ID to update stock: ");
        int productId = scanner.nextInt();
        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter threshold quantity: ");
        int thresholdQuantity = scanner.nextInt();

        Stock stock = findStockByProductId(productId);
        if (stock == null) {
            Stock newStock = new Stock(stockCounter++, product, quantity, thresholdQuantity);
            stocks.add(newStock);
            transactionHistoryList.add(new TransactionHistory(transactionCounter++, newStock, "Update", quantity));
            System.out.println("New stock created: " + newStock);
        } else {
            stock.quantity = quantity;
            stock.thresholdQuantity = thresholdQuantity;
            transactionHistoryList.add(new TransactionHistory(transactionCounter++, stock, "Update", quantity));
            System.out.println("Stock updated: " + stock);
        }
    }

    public static void restockProduct() {
        System.out.print("Enter product ID to restock: ");
        int productId = scanner.nextInt();
        Stock stock = findStockByProductId(productId);
        if (stock == null) {
            System.out.println("Stock not found for this product.");
            return;
        }

        System.out.print("Enter quantity to restock: ");
        int restockQty = scanner.nextInt();
        stock.restock(restockQty);
        transactionHistoryList.add(new TransactionHistory(transactionCounter++, stock, "Restock", restockQty));
        System.out.println("Restocked successfully: " + stock);
    }

    public static void makeSale() {
        System.out.print("Enter product ID to make sale: ");
        int productId = scanner.nextInt();
        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        Stock stock = findStockByProductId(productId);
        if (stock == null) {
            System.out.println("No stock available for this product.");
            return;
        }

        System.out.print("Enter quantity sold: ");
        int quantitySold = scanner.nextInt();
        if (!stock.checkStockAvailability(quantitySold)) {
            System.out.println("Not enough stock.");
            return;
        }

        stock.updateStock(quantitySold);
        double salePrice = product.price * quantitySold;
        Sales sale = new Sales(saleCounter++, product, quantitySold, salePrice);
        salesList.add(sale);

        transactionHistoryList.add(new TransactionHistory(transactionCounter++, stock, "Sale", quantitySold));
        System.out.println("Sale completed: " + sale);
    }

    public static void viewProductList() {
        System.out.println("\nProducts List:");
        if (products.isEmpty()) {
            System.out.println("No products available.");
        }
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void viewStockList() {
        System.out.println("\nStock List:");
        if (stocks.isEmpty()) {
            System.out.println("No stock entries.");
        }

        for (Stock stock : stocks) {
            System.out.println(stock);
            if (stock.quantity <= stock.thresholdQuantity) {
                System.out.println("⚠️ ALERT: Low stock for product '" + stock.product.productName + "'. Please restock!");
            }
        }
    }

    public static void viewTransactionHistory() {
        System.out.println("\nTransaction History:");
        if (transactionHistoryList.isEmpty()) {
            System.out.println("No transactions found.");
        }
        for (TransactionHistory transaction : transactionHistoryList) {
            System.out.println(transaction);
        }
    }

    public static Product findProductById(int productId) {
        for (Product product : products) {
            if (product.productId == productId) {
                return product;
            }
        }
        return null;
    }

    public static Stock findStockByProductId(int productId) {
        for (Stock stock : stocks) {
            if (stock.product.productId == productId) {
                return stock;
            }
        }
        return null;
    }
}