package stockmanagementsystem;

public class Supplier {
    int supplierId;
    String supplierName;

    public Supplier(int supplierId, String supplierName) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Supplier ID: " + supplierId + ", Name: " + supplierName;
    }
}