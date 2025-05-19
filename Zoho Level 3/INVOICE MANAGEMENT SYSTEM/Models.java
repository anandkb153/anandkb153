import java.util.ArrayList;
import java.util.List;

class Customer {
    String id;
    String name;

    Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Item {
    String name;
    int quantity;
    double price;

    Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class Invoice {
    String id;
    String customerId;
    List<Item> items;

    Invoice(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.quantity * item.price;
        }
        return total;
    }
}
