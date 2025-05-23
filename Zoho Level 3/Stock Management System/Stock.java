package stockmanagementsystem;

public class Stock {
    int stockId;
    Product product;
    int quantity;
    int thresholdQuantity;

    public Stock(int stockId, Product product, int quantity, int thresholdQuantity) {
        this.stockId = stockId;
        this.product = product;
        this.quantity = quantity;
        this.thresholdQuantity = thresholdQuantity;
    }

    public boolean checkStockAvailability(int quantityRequested) {
        return quantity >= quantityRequested;
    }

    public void updateStock(int quantitySold) {
        this.quantity -= quantitySold;
    }

    public void restock(int quantityAdded) {
        this.quantity += quantityAdded;
    }

    @Override
    public String toString() {
        return "Stock ID: " + stockId + ", Product: " + product.productName + ", Quantity: " + quantity + ", Threshold: " + thresholdQuantity;
    }
}