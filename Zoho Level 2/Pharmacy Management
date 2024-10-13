package ZOHO_L3_Programs;

import java.util.*;

public class Pharmacy {
	private List<Map<String, Object>> branches;
	private List<Map<String, Object>> customers;
	private List<Map<String, Object>> stock;
	private List<Map<String, Object>> transactions;
	private Map<String, String> alternateProducts;
	private Scanner scanner;
	int transactionCount=1;

	public Pharmacy() {
		branches = new ArrayList<>();
		customers = new ArrayList<>();
		transactions = new ArrayList<>();
		alternateProducts = new HashMap<>();
		stock = new ArrayList<>();
		scanner = new Scanner(System.in);
	}

	public void addBranch(int branchId, String location, long phoneNumber) {
		Map<String, Object> branch = new HashMap<>();
		branch.put("branchId", branchId);
		branch.put("location", location);
		branch.put("phoneNumber", phoneNumber);
		branches.add(branch);
		printBranchSummary();
	}

	public void addStock(int branchId, String medicine, int qty, int price, int one_product_price) {
		Map<String, Object> addstock = new HashMap<>();
		addstock.put("branchId", branchId);
		addstock.put("medicine", medicine);
		addstock.put("qty", qty);
		addstock.put("price", price);
		addstock.put("1_product_Price", one_product_price);
		stock.add(addstock);
		// Assuming stock details are added to branches separately
		printStockSummary();
	}

	public void associateAlternateProducts(String medicine, String alternate) {
		alternateProducts.put(medicine, alternate);
		printAlternateSummary();
	}

	public void addCustomer(int customerId, String name, long phoneNumber) {
		Map<String, Object> customer = new HashMap<>();
		customer.put("customerId", customerId);
		customer.put("name", name);
		customer.put("phoneNumber", phoneNumber);
		customers.add(customer);
		printCustomerSummary();
	}

	public void purchaseProducts(int customerId, int branchId, String medicine, int quantity)
	{
		int transactionId = transactionCount++;
		double price = getPrice(branchId, medicine, quantity);
		if (!isProductInStock(branchId, medicine, quantity)) {
			System.out.println("Product not available in sufficient quantity. Please choose a different product.");
			transactionId--;
			return;
		}

		// Check if the requested product is in stock
		
		Map<String, Object> transaction = new HashMap<>();
		transaction.put("branchId", branchId);
		transaction.put("transactionId", transactionId);
		transaction.put("customerId", customerId);
		transaction.put("medicine", medicine);
		transaction.put("quantity", quantity);
		transaction.put("price", price);

		transactions.add(transaction);

		while (true) {
			System.out.println("Do you want to continue ?(yes or no)");

			String responce = scanner.nextLine();
			if (responce.equalsIgnoreCase("yes")) {
				System.out.print("Enter Product: ");
				String newMedicine = scanner.nextLine();
				System.out.print("Enter Quantity: ");
				int newQuantity = scanner.nextInt();
				double newprice = getPrice(branchId, newMedicine, newQuantity);

				if (!isProductInStock(branchId, newMedicine, newQuantity)) {
					System.out.println(
							"Product not available in sufficient quantity. Please choose a different product.");
					return;
				}

				Map<String, Object> newtransaction = new HashMap<>();
				newtransaction.put("branchId", branchId);
				newtransaction.put("transactionId", transactionId);
				newtransaction.put("customerId", customerId);
				newtransaction.put("medicine", newMedicine);
				newtransaction.put("quantity", newQuantity);
				newtransaction.put("price", newprice);
				scanner.nextLine();

				transactions.add(newtransaction);
				

			} else {
				printTransactionSummary();
			
				break;

			}

		}
	}

	private boolean isProductInStock(int branchId, String medicine, int requestedQuantity) {
		// Implement logic to check stock availability based on branchId and medicine
		// You can use your existing data structures and logic for stock management
		// Return true if the product is available in sufficient quantity, otherwise
		// false
		// For simplicity, I'll assume that stock information is stored in a list of
		// maps
		for (Map<String, Object> stockEntry : stock) {
			String stockMedicine = (String) stockEntry.get("medicine");
			if (stockMedicine != null && stockMedicine.equalsIgnoreCase(medicine)) {
				Integer availableQuantityObject = (Integer) stockEntry.get("qty");
				if (availableQuantityObject != null) {
					int availableQuantity = availableQuantityObject.intValue();

					if (availableQuantity >= requestedQuantity)//10>=5
						stockEntry.put("qty", availableQuantityObject - requestedQuantity);

					return availableQuantity >= requestedQuantity;
				}
			}
		}
		return false; // Product not found in stock
	}

	private double getPrice(int branchId, String medicine, int quantity) {

		for (Map<String, Object> stockEntry : stock)
		{
			String stockMedicine = (String) stockEntry.get("medicine");
			if (stockMedicine != null && stockMedicine.equalsIgnoreCase(medicine))
			{
				
				int price=(Integer)stockEntry.get("1_product_Price");
					
					
					return quantity *price;
					
				
			}
		}
	
		return quantity * 0.00; // Replace with actual logic
	}

	public void printBranchSummary() {
		System.out.println("Branch ID      Branch Location       Phone number");
		System.out.println("--------------------------------------------------");
		for (Map<String, Object> branch : branches) {
			System.out.println(branch.get("branchId") + "          |        " + branch.get("location")
					+ "        |     " + branch.get("phoneNumber"));
			System.out.println("--------------------------------------------------");
		}
	}

	public void printStockSummary() {
		System.out.println("Branch ID      Medicine             Available Qty      Price");
		System.out.println("-------------------------------------------------------------");
		// Add logic to print stock details

		for (Map<String, Object> branch : branches) {
			int branchId = (int) branch.get("branchId");

			for (Map<String, Object> stock_obj : stock) {
				if ((int) stock_obj.get("branchId") == branchId) {
					String medicine = (String) stock_obj.get("medicine");
					int quantity = (int) stock_obj.get("qty");
					int price = (int) stock_obj.get("price");

					System.out.println(branchId + "          |    " + medicine + "         |         " + quantity
							+ "         |    " + price);
					System.out.println("-------------------------------------------------------------");
				}
			}
		}
	}

	public void printAlternateSummary() {
		System.out.println("Medicine       Alternate");
		System.out.println("------------------------");
		for (Map.Entry<String, String> entry : alternateProducts.entrySet()) {
			System.out.println(entry.getKey() + "    |      " + entry.getValue());
			System.out.println("------------------------");
		}
	}

	public void printCustomerSummary() {
		System.out.println("Customer ID         Customer Name         Phone number");
		System.out.println("-----------------------------------------------------");
		for (Map<String, Object> customer : customers) {
			System.out.println(customer.get("customerId") + "            |      " + customer.get("name")
					+ "              |   " + customer.get("phoneNumber"));
			System.out.println("-----------------------------------------------------");
		}
	}

	public void printTransactionSummary() {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Branch ID      Transaction ID   Customer ID    Medicine   Quantity      Price");
		System.out.println("-------------------------------------------------------------------------------");
		for (Map<String, Object> transaction : transactions) {
			System.out.println(" " + transaction.get("branchId") + "                  "
					+ transaction.get("transactionId") + "                " + transaction.get("customerId")
					+ "          " + transaction.get("medicine") + "       " + transaction.get("quantity")
					+ "           " + transaction.get("price"));
			System.out.println("-------------------------------------------------------------------------------");
		}
	}

	private void printSummary(int customerId) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Branch ID      Transaction ID       Medicine   Quantity      Price");
		System.out.println("-------------------------------------------------------------------------------");
		
		for (Map<String, Object> customer : customers) 
		{
			
			int c_id=(int)customer.get("customerId");
			
			for (Map<String, Object> transaction : transactions) {
				
				if((int)transaction.get("customerId")==c_id && customerId==c_id)
				{
					
					System.out.println(" " + transaction.get("branchId") + "                  "
							+ transaction.get("transactionId") + "                "
							+ transaction.get("medicine") + "       " + transaction.get("quantity")
							+ "           " + transaction.get("price"));
					System.out.println("-------------------------------------------------------------------------------");
				}
				
			}
			
			
		}
	}

	public static void main(String[] args) {
		Pharmacy pharmacy = new Pharmacy();
		Scanner scanner = new Scanner(System.in);
		int choice;
		while (true) {
			System.out.println("\n1. Add Branch");
			System.out.println("2. Add Stock");
			System.out.println("3. Associate Alternate Products");
			System.out.println("4. Add Customer");
			System.out.println("5. Purchase Products");
			System.out.println("6. Print Summary");
			System.out.println("7. Exit");

			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline character

			switch (choice) {
			case 1:
				System.out.print("Enter Branch ID: ");
				int branchId = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character
				System.out.print("Enter Branch Location: ");
				String location = scanner.nextLine();
				System.out.print("Enter Phone Number: ");
				long phoneNumber = scanner.nextLong();

				pharmacy.addBranch(branchId, location, phoneNumber);

				break;
			case 2:
				System.out.print("Enter Branch ID: ");
				branchId = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character
				System.out.print("Enter Medicine: ");
				String medicine = scanner.nextLine();
				System.out.print("Enter Quantity: ");
				int quantity = scanner.nextInt();
				System.out.print("Enter Price: ");
				int price = scanner.nextInt();
				
				int One_product_price=price/quantity;

				pharmacy.addStock(branchId, medicine, quantity, price,One_product_price);
				break;
			case 3:
				System.out.print("Enter Medicine: ");
				medicine = scanner.nextLine();
				System.out.print("Enter Alternate: ");
				String alternate = scanner.nextLine();
				pharmacy.associateAlternateProducts(medicine, alternate);
				break;
			case 4:
				System.out.print("Enter Customer ID: ");
				int customerId = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character
				System.out.print("Enter Customer Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter Phone Number: ");
				phoneNumber = scanner.nextLong();
				pharmacy.addCustomer(customerId, name, phoneNumber);

				break;
			case 5:
				System.out.print("Enter Customer ID: ");
				customerId = scanner.nextInt();
				System.out.print("Enter Branch ID: ");
				branchId = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character
				System.out.print("Enter Product: ");
				medicine = scanner.nextLine();
				System.out.print("Enter Quantity: ");
				quantity = scanner.nextInt();
				pharmacy.purchaseProducts(customerId, branchId, medicine, quantity);

				break;
			case 6:
				System.out.print("Enter Customer ID: ");
				customerId = scanner.nextInt();
				pharmacy.printSummary(customerId);
				break;
			case 7:
				System.out.println("Exiting...Thanks for Visite Our Pharamcy");
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

}
