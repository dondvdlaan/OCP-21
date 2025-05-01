package dev.manyroads.projects.simplebankingsystem.stage3.example2;

import java.util.concurrent.ThreadLocalRandom;

public class Card {

    private String number;
    private String pin;
    private double balance = 0.0;

    public Card() {
        this.number = generateCardNumber();
        this.pin = generatePin();
    }

    public Card(String number, String pin, double balance) {
        this.number = number;
        this.pin = pin;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (!getNumber().equals(card.getNumber())) return false;
        return getPin().equals(card.getPin());
    }

    @Override
    public int hashCode() {
        int result = getNumber().hashCode();
        result = 31 * result + getPin().hashCode();
        return result;
    }

    private String generatePin() {
        int pinInteger = ThreadLocalRandom.current().nextInt(0, 10_000);
        return String.format("%04d", pinInteger);
    }

    private String generateCardNumber() {
        final String IIN = "400000";
        long random9digitsInt = ThreadLocalRandom.current().nextLong(1_000_000_000);
        String random9digitsString = String.format("%09d", random9digitsInt);
        String firstFifteenDigits = IIN + random9digitsString;
        return firstFifteenDigits + generateLastDigit(firstFifteenDigits);
    }

    //implements Luhn's Algorithm
    private Character generateLastDigit(String firstFifteenDigits) {
        int sum = 0;

        for (int i = 0; i < 15; i++) {
            int intValue;

            if ((i + 1) % 2 != 0) {
                intValue = Character.getNumericValue(firstFifteenDigits.charAt(i));
                intValue *= 2;
                if (intValue > 9) {
                    intValue -= 9;
                }
            } else {
                intValue = Character.getNumericValue(firstFifteenDigits.charAt(i));
            }
            sum += intValue;
        }

        int returnValue = sum % 10;
        if (returnValue != 0) {
            returnValue = 10 - returnValue;
        }
        return (char) (returnValue + '0');
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

}
