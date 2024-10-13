package Billing_mangement_super_Market;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private String username;
    private String password;
    private List<Product> productList = new ArrayList<>();
    private double eligibleAmount;
    private double offerPercentage;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public void addProduct(Product product) {
        if (isProductIdUnique(product.getId())) {
            productList.add(product);
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Error: Product ID must be unique.");
        }
    }

    public boolean isProductIdUnique(int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return false;
            }
        }
        return true;
    }

    public void removeProduct(int productId) {
        productList.removeIf(product -> product.getId() == productId);
    }

    public void viewProducts() {
        if (productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available Products:");
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setOffer(double eligibleAmount, double offerPercentage) {
        this.eligibleAmount = eligibleAmount;
        this.offerPercentage = offerPercentage;
        System.out.println("Offer set: Buy for " + eligibleAmount + " or more to get " + offerPercentage + "% off.");
    }

    public double applyOffer(double totalAmount) {
        if (totalAmount >= eligibleAmount) {
            return totalAmount - (totalAmount * (offerPercentage / 100));
        } else {
            return totalAmount;
        }
    }
}
