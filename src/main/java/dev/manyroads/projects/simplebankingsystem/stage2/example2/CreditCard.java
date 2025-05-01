package dev.manyroads.projects.simplebankingsystem.stage2.example2;

import java.util.*;

public class CreditCard {
    private final String number;
    private String pin;
    private double balance = 0;
    private double limit = 0;

    public CreditCard(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public boolean validatePin(String pin) {
        return this.pin != null && this.pin.equals(pin);
    }

    public String generatePin(int size) {
        Random random = new Random();
        StringBuilder pin = new StringBuilder();
        for (int i = 0; i < size; i++) {
            pin.append(random.nextInt(10));
        }
        this.pin = pin.toString();
        return this.pin;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getBalance() {
        return balance;
    }

    public boolean setBalance(double balance) {
        if (this.balance < limit) {
            return false;
        }
        this.balance = balance;
        return true;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (this.balance - amount < limit) {
            return false;
        }
        this.balance -= amount;
        return true;
    }
}
