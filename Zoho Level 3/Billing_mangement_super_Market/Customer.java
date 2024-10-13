package Billing_mangement_super_Market;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String username;
    private String password;
    private Cart cart = new Cart();
    private List<String> transactionHistory = new ArrayList<>();

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void addToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    public void viewProducts(List<Product> productList) {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available Products:");
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

    public double checkout() {
        return cart.calculateTotal();
    }

    public void displayCart() {
        cart.displayCart();
    }

    public boolean isCartEmpty() {
        return cart.isEmpty();
    }

    public String confirmPurchase(double finalAmount) {
        if (finalAmount > 0) {
            String transactionDetails = cart.getCartDetails() + "Total Amount: " + finalAmount;
            transactionHistory.add(transactionDetails);
            cart.clearCart(); // Clear the cart after purchase
            return transactionDetails;
        }
        return null;
    }

    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
                System.out.println("-----");
            }
        }
    }
}
