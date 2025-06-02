package dev.manyroads.projects.budgetmanager.stage2.example2;


import java.util.LinkedHashMap;
import java.util.Scanner;

class Manager {
    static Scanner scanner = new Scanner(System.in);
    static LinkedHashMap<String, Double> expenses = new LinkedHashMap<>();
    static double balance = 0;

    static void printBalance() {
        System.out.printf("%nBalance: $%.2f%n%n", balance);
    }

    static void addIncome() {
        try {
            System.out.println("\nEnter income:");
            balance += Double.parseDouble(scanner.nextLine());
            System.out.println("Income was added!\n");
        } catch (Exception e) {
            System.out.println("Wrong input!\n");
        }
    }

    static void printPurchases() {
        if (expenses.size() == 0) System.out.println("\nThe purchase list is empty\n");
        else {
            double total = 0;
            System.out.println();
            for (var entry : expenses.entrySet()) {
                System.out.printf("%s $%.2f%n", entry.getKey(), entry.getValue());
                total += entry.getValue();
            }
            System.out.printf("Total sum: $%.2f%n%n", total);
        }
    }

    static void addPurchase() {
        try {
            System.out.println("\nEnter purchase name:");
            String input = scanner.nextLine();
            System.out.println("Enter its price:");
            double price = Double.parseDouble(scanner.nextLine());
            if (balance - price >= 0) {
                expenses.put(input, price);
                balance -= price;
                System.out.println("Purchase was added!\n");
            } else System.out.println("Not enough money!\n");
        } catch (Exception e) {
            System.out.println("Wrong input!\n");
        }
    }
}

class Menu extends Manager {
    static boolean run = true;

    static void exit() {
        run = false;
        System.out.println("\nBye!");
    }

    static int menuButton() {
        try {
            String input = scanner.nextLine();
            if (input.matches("[01234]")) return Integer.parseInt(input);
            else throw new Exception();
        } catch (Exception e) {
            return -1;
        }
    }

    static void printMenu() {
        System.out.println("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                0) Exit""");
    }

    static void wrongInput() {
        System.out.println("Wrong input!");
    }
}

public class Main extends Menu {
    public static void main(String[] args) {

        while (run) {
            printMenu();
            switch (menuButton()) {
                case 1 -> addIncome();
                case 2 -> addPurchase();
                case 3 -> printPurchases();
                case 4 -> printBalance();
                case 0 -> exit();
                default -> wrongInput();
            }
        }
    }
}