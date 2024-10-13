package Gift_Card_System;

import java.util.*;

public class Customer {
    private int id;
    private String accountNo;
    private String name;
    private int balance;
    private String encryptedPassword;
    private GiftCard giftCard;

    public Customer(int id, String accountNo, String name, int balance, String encryptedPassword) {
        this.id = id;
        this.accountNo = accountNo;
        this.name = name;
        this.balance = balance;
        this.encryptedPassword = encryptPassword(encryptedPassword);
        this.giftCard = null;
    }

    public int getId() {
        return id;
    }
    public int getBalance()
    {
    	return balance;
    }
    public String getName() {
		return name;
	}


	public boolean verifyPassword(String password) {
        return encryptedPassword.equals(encryptPassword(password));
    }

    private String encryptPassword(String password) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                encrypted.append(c == 'z' ? 'a' : (char) (c + 1));
            } else if (Character.isUpperCase(c)) {
                encrypted.append(c == 'Z' ? 'A' : (char) (c + 1));
            } else if (Character.isDigit(c)) {
                encrypted.append(c == '9' ? '0' : (char) (c + 1));
            }
        }
        return encrypted.toString();
    }

    public void createNewGiftCard(Scanner scanner) {
        System.out.print("Enter Gift Card Amount: ");
        int amount = scanner.nextInt();
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        giftCard = new GiftCard(amount);
        System.out.println("Gift Card created with number: " + giftCard.getCardNumber());
    }

    public void topUpGiftCard(Scanner scanner) {
        if (giftCard == null || giftCard.isBlocked()) {
            System.out.println("No active gift card or gift card is blocked.");
            return;
        }
        System.out.print("Enter Top-up Amount: ");
        int amount = scanner.nextInt();
        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }
        balance -= amount;
        giftCard.topUp(amount);
        System.out.println("Gift Card topped up. Current balance: " + giftCard.getBalance());
    }

    public void showTransactionHistory() {
        if (giftCard == null) {
            System.out.println("No active gift card.");
            return;
        }
        giftCard.showTransactionHistory();
    }

    public void blockGiftCard() {
        if (giftCard == null || giftCard.isBlocked()) {
            System.out.println("No active gift card or gift card is already blocked.");
            return;
        }
        balance += giftCard.block();
        System.out.println("Gift card blocked. Remaining balance returned to account.");
    }

    public boolean validateGiftCard(String cardNumber, int pin, int amount) {
        if (giftCard == null || giftCard.isBlocked()) {
            return false;
        }
        return giftCard.validate(cardNumber, pin, amount);
    }

    public int getGiftCardBalance() {
        return giftCard.getBalance();
    }
}
