package stockmanagementsystem;

public class Product {
    int productId;
    String productName;
    double price;
    Supplier supplier;

    public Product(int productId, String productName, double price, Supplier supplier) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Price: " + price;
    }
}