package dev.manyroads.projects.simplebankingsystem.stage4.example3;

import java.util.Random;

public class Card {
    private final String number;
    private final String PIN;
    private int balance = 0;

    public Card() {
        number = generateRandomCardNumber();
        PIN = generateRandomPIN();
    }

    public Card(String number, String PIN, int balance) {
        this.number = number;
        this.PIN = PIN;
        this.balance = balance;
    }


    private String generateRandomCardNumber() {
        Random random = new Random();
        String MII = "4";
        String BIN = MII + "00000";
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            accountNumber.append(random.nextInt(10));
        }

        String idNumber = BIN + accountNumber;
        String checkSum = Luhn.generateValidChecksum(idNumber);

        return idNumber + checkSum;
    }

    private String generateRandomPIN() {
        Random random = new Random();
        String firstDigit = String.valueOf(random.nextInt(10));
        String secondDigit = String.valueOf(random.nextInt(10));
        String thirdDigit = String.valueOf(random.nextInt(10));
        String fourthDigit = String.valueOf(random.nextInt(10));

        return firstDigit + secondDigit + thirdDigit + fourthDigit;
    }


    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public String getPIN() {
        return PIN;
    }

}