package dev.manyroads.projects.simplebankingsystem.stage3.example2;

import org.sqlite.SQLiteDataSource;

import java.util.Scanner;

public class Controller {
    private static JdbcCardDAO cardDAO;
    private Card card;

    public void start(String dbName) {
        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl("jdbc:sqlite:" + dbName);
        cardDAO = new JdbcCardDAO(sqLiteDataSource);
        cardDAO.init();
        userDisplay();
    }

    private void userDisplay() {
        boolean loggedIn = false;
        boolean loopCondition = true;
        Scanner scanner = new Scanner(System.in);

        while (loopCondition) {

            if (!loggedIn) {
                System.out.println("1. Create account\n" +
                        "2. Log into account\n" +
                        "0. Exit");
                int input = scanner.nextInt();

                switch (input) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        loggedIn = login();
                        break;
                    case 0:
                        loopCondition = false;
                        System.out.println("\nBye!");
                        break;
                    default:
                }
            } else {
                System.out.println("1. Balance\n" +
                        "2. Log out\n" +
                        "0. Exit");
                int input = scanner.nextInt();

                switch (input) {
                    case 1:
                        printBalance();
                        break;
                    case 2:
                        loggedIn = false;
                        System.out.println("\nYou have successfully logged out!\n");
                        break;
                    case 0:
                        loopCondition = false;
                        System.out.println("\nBye!");
                        break;
                    default:
                }
            }
        }
    }

    private boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter your card number:");
        String number = scanner.next();
        System.out.println("Your card PIN:");
        String pin = scanner.next();

        card = cardDAO.findByNumber(number);

        if (card != null && card.getNumber().equals(number) && card.getPin().equals(pin)) {
            System.out.println("\nYou have successfully logged in!\n");
            return true;
        } else {
            System.out.println("\nWrong card number or PIN!\n");
            return false;
        }
    }

    private void printBalance() {
        double balance = card.getBalance();
        if (balance == (long) balance) {
            System.out.println("\nBalance: " + (long) balance);
        } else {
            System.out.println("\nBalance: " + balance);
        }
        System.out.println();
    }

    private void createAccount() {
        Card card = new Card();
        cardDAO.add(card);
        System.out.println();
        System.out.format("Your card have been created\n" +
                "Your card number:\n" +
                "%s\n" +
                "Your card PIN:\n" +
                "%s\n", card.getNumber(), card.getPin());
        System.out.println();
    }
}