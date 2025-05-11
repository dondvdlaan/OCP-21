package dev.manyroads.projects.simplebankingsystem.stage4.example3;

import java.util.Scanner;

public class Main {
    private static boolean isActive = true;
    private static Card loggedInCard;

    public static Card getLoggedInCard() {
        return loggedInCard;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardSystem cardSystem = CardSystem.getInstance();
        String dbFilePath = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-fileName")) {
                dbFilePath = args[i + 1];
                break;
            }
        }

        if (dbFilePath.isEmpty()) {
            System.out.println("Database path argument is not specified!");
            return;
        }


        BankDB.setUrl("jdbc:sqlite:" + dbFilePath);
        BankDB.createOrConnectToDBFile();
        BankDB.createCardTableIfNotExist();

        do {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    Card newCard = cardSystem.addCard();
                    System.out.println("Your card number:");
                    System.out.println(newCard.getNumber());
                    System.out.println("Your card PIN:");
                    System.out.println(newCard.getPIN());
                }
                case "2" -> isActive = openLoginCLI(scanner);
                case "0" -> isActive = false;
                default -> System.out.println("Please input a number between 1, 2, or 0");
            }

        } while (isActive);

    }

    private static boolean openLoginCLI(Scanner scanner) {
        CardSystem cardSystem = CardSystem.getInstance();
        System.out.println("Enter your card number:");
        String cardNumber = scanner.nextLine();
        System.out.println("Enter your PIN:");
        String PIN = scanner.nextLine();

        loggedInCard = cardSystem.loginWithCredentials(cardNumber, PIN);
        boolean success = loggedInCard != null;

        if (!success) {
            System.out.println("Wrong card number or PIN!");
            return true;
        }

        System.out.println("You have successfully logged in!");

        while (true) {
            System.out.println("1. Balance");
            System.out.println("2. Add Income");
            System.out.println("3. Do Transfer");
            System.out.println("4. Close Account");
            System.out.println("5. Log out");
            System.out.println("0. Exit");


            String input = scanner.nextLine();

            switch (input) {
                case "1" -> {
                    int balance = loggedInCard.getBalance();
                    System.out.println(balance);
                }
                case "2" -> {
                    openAddIncomeCLI(scanner);
                }
                case "3" -> {
                    openTransferCLI(scanner);
                }
                case "4" -> {
                    boolean deleteSuccess = cardSystem.closeAccount(loggedInCard);
                    if (deleteSuccess) {
                        loggedInCard = null;
                        return true;
                    }
                }
                case "5" -> {
                    loggedInCard = null;
                    return true;
                }
                case "0" -> {
                    return false;
                }
                default -> System.out.println("Input a number between 0 - 5");
            }

        }

    }

    private static void openAddIncomeCLI(Scanner scanner) {
        CardSystem cardSystem = CardSystem.getInstance();
        System.out.println("Enter Income:");
        try {
            int amountToAdd = Integer.parseInt(scanner.nextLine());
            cardSystem.addBalance(loggedInCard, amountToAdd);
            System.out.println("Income was added!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid input amount!" + e.getMessage());
        }
    }

    private static void openTransferCLI(Scanner scanner) {
        System.out.println("Transfer");
        System.out.println("Enter Card Number:");
        CardSystem cardSystem = CardSystem.getInstance();
        try {
            String number = scanner.nextLine();
            ValidityInfo validityInfo = cardSystem.checkTransferValidity(number);

            if (!validityInfo.valid) {
                System.out.println(validityInfo.message);
                return;
            }

            Card cardToTransfer = cardSystem.getCardByNumber(number);
            if (cardToTransfer == null) {
                System.out.println("Such a card does not exist.");
                return;
            }

            // card valid and exist.
            System.out.println("Enter how much money you want to transfer:");
            int transferAmount = Integer.parseInt(scanner.nextLine());
            if (transferAmount > loggedInCard.getBalance()) {
                System.out.println("Not enough money!");
                return;
            }

            // valid and enough balance
            boolean success = cardSystem.transferMoney(loggedInCard, cardToTransfer, transferAmount);
            if (success) {
                System.out.println("Success!");
            } else {
                System.out.println("Transfer failed!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
