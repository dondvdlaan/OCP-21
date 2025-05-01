package dev.manyroads.projects.simplebankingsystem.stage2.example2;

import java.io.PrintStream;
import java.util.*;

public class SimpleBankingSystem {
    private static final Scanner KEYPAD = new Scanner(System.in);
    private static final PrintStream SCREEN = System.out;
    private static final String BIN = "400000";
    private static final int ACCOUNT_NUMBER_SIZE = 9;
    private static final int PIN_SIZE = 4;

    private final Database database = new Database();

    public SimpleBankingSystem() {
        while (true) {
            SCREEN.printf("1. Create an account%n2. Log into account%n0. Exit%n");
            int key = KEYPAD.nextInt();
            if (key == 0) {
                this.exit();
            }
            if (key == 1) {
                this.createAccount();
            }
            if (key == 2) {
                this.logIntoAccount();
            }
        }
    }

    private void createAccount() {
        String creditCardNumber;
        do {
            creditCardNumber = BIN + this.generateAccountNumber(ACCOUNT_NUMBER_SIZE);
            creditCardNumber += getLuhnNumber(creditCardNumber);
        } while (this.database.hasCreditCard(creditCardNumber));
        CreditCard creditCard = new CreditCard(creditCardNumber);
        SCREEN.printf("%nYour card has been created%n");
        SCREEN.printf("Your card number:%n%s%n", creditCard.getNumber());
        SCREEN.printf("Your card PIN:%n%s%n%n", creditCard.generatePin(PIN_SIZE));
        this.database.addCreditCard(creditCard);
    }

    private void logIntoAccount() {
        SCREEN.printf("%nEnter your card number:%n");
        String creditCardNumber = KEYPAD.next();
        SCREEN.printf("Enter your PIN:%n");
        String creditCardPIN = KEYPAD.next();
        if (this.validateLuhnNumber(creditCardNumber) && this.database.hasCreditCard(creditCardNumber)) {
            CreditCard creditCard = this.database.getCreditCard(creditCardNumber);
            if (creditCard.validatePin(creditCardPIN)) {
                SCREEN.printf("%nYou have successfully logged in!%n%n");
                this.viewAccount(creditCard);
                SCREEN.printf("%nYou have successfully logged out!%n%n");
                return;
            }
        }
        SCREEN.printf("%nWrong card number or PIN!%n%n");
    }

    private void viewAccount(CreditCard creditCard) {
        while (true) {
            SCREEN.println("1. Balance\n2. Log out\n0. Exit");
            int key = KEYPAD.nextInt();
            if (key == 0) {
                this.exit();
            }
            if (key == 1) {
                SCREEN.printf("%nBalance: %.0f%n%n", creditCard.getBalance());
            }
            if (key == 2) {
                return;
            }
        }
    }

    private String generateAccountNumber(int size) {
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < size; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }

    private int getLuhnNumber(String number) {
        int[] numbers = Arrays.stream(number.split("")).mapToInt(Integer::parseInt).toArray();
        int c = 0;
        for (int i = 0; i < numbers.length; i++) {
            c += i % 2 == 0 ? numbers[i] > 4 ? numbers[i] * 2 - 9 : numbers[i] * 2 : numbers[i];
        }
        return (10 - c % 10) % 10;
    }

    private boolean validateLuhnNumber(String number) {
        int c1 = Integer.parseInt(number.substring(number.length() - 1));
        int c2 = this.getLuhnNumber(number.substring(0, number.length() - 1));
        return c1 == c2;
    }

    private void exit() {
        SCREEN.printf("%nBye!%n");
        System.exit(1);
    }
}
