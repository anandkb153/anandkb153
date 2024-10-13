package Billing_mangement_super_Market;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public double calculateTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("Your cart:");
        for (CartItem item : items) {
            System.out.println("Product: " + item.getProduct().getName() + ", Quantity: " + item.getQuantity() + ", Price: " + item.getProduct().getPrice());
        }
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clearCart() {
        items.clear();
    }

    public String getCartDetails() {
        StringBuilder details = new StringBuilder();
        for (CartItem item : items) {
            details.append("Product: ").append(item.getProduct().getName())
                   .append(", Quantity: ").append(item.getQuantity())
                   .append(", Subtotal: ").append(item.getProduct().getPrice() * item.getQuantity())
                   .append("\n");
        }
        return details.toString();
    }
}
