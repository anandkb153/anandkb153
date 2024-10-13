package Gift_Card_System;

import java.util.*;

public class GiftCard {
    private static int cardCounter = 10000;
    private String cardNumber;
    private int pin;
    private int balance;
    private boolean blocked;
    private List<String> transactionHistory;
    private static final Set<Integer> usedPins = new HashSet<>();

    public GiftCard(int initialBalance) {
        this.cardNumber = String.valueOf(++cardCounter);
        this.pin = generatePin( ); // Generate random 4-digit PIN
        this.balance = initialBalance;
        this.blocked = false;
        this.transactionHistory = new ArrayList<>();
        System.out.println("Gift Card created. Card Number: " + cardNumber + ", PIN: " + pin);
    }

    private int generatePin() {
    	    int pin = (int) (Math.random() * 9000) + 1000;
    	    
    	    if(usedPins.contains(pin))
    	    	return generatePin();
    	    else
    	    {
    	    	usedPins.add(pin);
    	    }
    	   
		return pin;
	}

	

	public String getCardNumber() {
        return cardNumber;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void topUp(int amount) {
        balance += amount;
        transactionHistory.add("Top-up: " + amount);
    }

    public void showTransactionHistory() {
        System.out.println("\n--- Gift Card Transaction History ---");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        }
    }

    public boolean validate(String cardNumber, int pin, int amount) {
        if (!this.cardNumber.equals(cardNumber) || this.pin != pin || amount > balance) {
            return false;
        }
        balance -= amount;
        transactionHistory.add("Purchase: " + amount);
        return true;
    }

    public int block() {
        blocked = true;
        return balance;
    }
}
