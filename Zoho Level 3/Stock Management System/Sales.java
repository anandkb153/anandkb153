package stockmanagementsystem;

public class Sales {
    int saleId;
    Product product;
    int quantitySold;
    double salePrice;

    public Sales(int saleId, Product product, int quantitySold, double salePrice) {
        this.saleId = saleId;
        this.product = product;
        this.quantitySold = quantitySold;
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "Sale ID: " + saleId + ", Product: " + product.productName + ", Quantity Sold: " + quantitySold + ", Total Sale Price: " + salePrice;
    }
}