package stockmanagementsystem;

public class TransactionHistory {
    int transactionId;
    Stock stock;
    String transactionType;  // "Sale", "Restock", "Update"
    int quantityChanged;

    public TransactionHistory(int transactionId, Stock stock, String transactionType, int quantityChanged) {
        this.transactionId = transactionId;
        this.stock = stock;
        this.transactionType = transactionType;
        this.quantityChanged = quantityChanged;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", Stock ID: " + stock.stockId + ", Type: " + transactionType + ", Quantity Changed: " + quantityChanged;
    }
}