import java.util.HashMap;
import java.util.Map;

class InvoiceService {
    private final Map<String, Customer> customers = new HashMap<>();
    private final Map<String, Invoice> invoices = new HashMap<>();

    public void addCustomer(String id, String name) {
        customers.put(id, new Customer(id, name));
        System.out.println("Customer added successfully.");
    }

    public void addInvoice(String id, String customerId) {
        if (!customers.containsKey(customerId)) {
            System.out.println("Customer not found!");
            return;
        }
        invoices.put(id, new Invoice(id, customerId));
        System.out.println("Invoice added successfully.");
    }

    public void addItemToInvoice(String invoiceId, String itemName, int quantity, double price) {
        Invoice invoice = invoices.get(invoiceId);
        if (invoice == null) {
            System.out.println("Invoice not found!");
            return;
        }
        invoice.items.add(new Item(itemName, quantity, price));
        System.out.println("Item added successfully.");
    }

    public void listAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        System.out.println("--- Customers ---");
        for (Customer customer : customers.values()) {
            System.out.println("ID: " + customer.id + ", Name: " + customer.name);
        }
    }

    public void listAllInvoices() {
        if (invoices.isEmpty()) {
            System.out.println("No invoices found.");
            return;
        }
        System.out.println("--- Invoices ---");
        for (Invoice invoice : invoices.values()) {
            System.out.println("Invoice ID: " + invoice.id + ", Customer ID: " + invoice.customerId);
        }
    }

    public void listInvoicesOfCustomer(String customerId) {
        boolean found = false;
        System.out.println("--- Invoices for Customer ID: " + customerId + " ---");
        for (Invoice invoice : invoices.values()) {
            if (invoice.customerId.equals(customerId)) {
                System.out.println("Invoice ID: " + invoice.id);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No invoices found for this customer.");
        }
    }

    public void displayInvoiceDetails(String invoiceId) {
        Invoice invoice = invoices.get(invoiceId);
        if (invoice == null) {
            System.out.println("Invoice not found!");
            return;
        }
        System.out.println("--- Invoice Details ---");
        System.out.println("Invoice ID: " + invoice.id);
        System.out.println("Customer ID: " + invoice.customerId);
        System.out.println("Items:");
        for (Item item : invoice.items) {
            System.out.printf(" - %s: %d x %.2f = %.2f%n",
                    item.name, item.quantity, item.price, item.quantity * item.price);
        }
        System.out.printf("Total Amount: %.2f%n", invoice.calculateTotal());
    }
}
