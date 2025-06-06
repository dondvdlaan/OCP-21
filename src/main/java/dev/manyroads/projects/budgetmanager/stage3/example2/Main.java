package dev.manyroads.projects.budgetmanager.stage3.example2;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

class Reports {
    private final Ledger ledger;

    Reports(Ledger ledger) {
        this.ledger = ledger;
    }

    void allPurchases() {
        purchasesByCondition(purchase -> true);
    }

    void purchasesForCategory(Category category) {
        purchasesByCondition(purchase -> purchase.category() == category);
    }

    private void purchasesByCondition(Predicate<Purchase> condition) {
        ledger.purchases()
                .filter(condition)
                .forEach(purchase -> System.out.printf(
                        "%s $%.2f\n", purchase.description(), purchase.amount()));

        double total = ledger.purchases().filter(condition).mapToDouble(Purchase::amount).sum();
        System.out.printf("Total sum: $%.2f\n", total);
    }
}

enum Category {Food, Clothes, Entertainment, Other}


class Menu {
    private final Ledger ledger;
    private final Reports reports;
    private final Scanner scanner;

    private static final String MAIN_MENU = """
            
            Choose your action:
            1) Add income
            2) Add purchase
            3) Show list of purchases
            4) Balance
            0) Exit""";

    // For efficiency, keep a static copy of the array of Category instances
    private static final Category[] CATEGORIES = Category.values();

    private static final String ADD_PURCHASE_MENU;
    private static final String LIST_PURCHASES_MENU;

    static {
        StringBuilder addPurchaseMenu = new StringBuilder("\nChoose the type of purchase\n");
        StringBuilder listPurchasesMenu = new StringBuilder("\nChoose the type of purchases\n");

        int menuOption = 1;
        for (Category category : CATEGORIES) {
            addPurchaseMenu.append(menuOption).append(") ").append(category).append('\n');
            listPurchasesMenu.append(menuOption).append(") ").append(category).append('\n');
            menuOption++;
        }

        addPurchaseMenu.append(menuOption).append(") Back");
        listPurchasesMenu.append(menuOption).append(") All\n");
        listPurchasesMenu.append(++menuOption).append(") Back");

        ADD_PURCHASE_MENU = addPurchaseMenu.toString();
        LIST_PURCHASES_MENU = listPurchasesMenu.toString();
    }

    Menu(Ledger ledger, Scanner scanner) {
        this.ledger = ledger;
        this.reports = new Reports(ledger);
        this.scanner = scanner;
    }

    void run() {
        while (true) {
            System.out.println(MAIN_MENU);
            switch (scanner.nextLine()) {
                case "1" -> addIncome();
                case "2" -> runAddPurchaseMenu();
                case "3" -> runListPurchasesMenu();
                case "4" -> showBalance();
                case "0" -> {
                    System.out.println("\nBye!");
                    return;
                }
                default -> System.out.println("\nPlease enter a number between 0 and 4");
            }
        }
    }

    void addIncome() {
        System.out.println("\nEnter income:");
        double amount = Double.parseDouble(scanner.nextLine());
        ledger.addIncome(amount);
        System.out.println("Income was added!");
    }

    private void runAddPurchaseMenu() {
        String inputErrorMessage
                = "\nPlease enter a number between 1 and %d".formatted(CATEGORIES.length + 1);

        while (true) {
            System.out.println(ADD_PURCHASE_MENU);
            String input = scanner.nextLine();
            try {
                int selection = Integer.parseInt(input);
                if (1 <= selection && selection <= CATEGORIES.length)
                    addPurchase(CATEGORIES[selection - 1]);
                else if (selection == CATEGORIES.length + 1)
                    return;
                else
                    System.out.println(inputErrorMessage);
            } catch (NumberFormatException e) {
                System.out.println(inputErrorMessage);
            }
        }
    }

    void addPurchase(Category category) {
        System.out.println("\nEnter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price:");
        double amount = Double.parseDouble(scanner.nextLine());
        ledger.addPurchase(category, name, amount);
        System.out.println("Purchase was added!");
    }

    void runListPurchasesMenu() {
        if (!ledger.hasPurchases()) {
            System.out.println("\nThe purchase list is empty!");
            return;
        }

        String inputErrorMessage
                = "\nPlease enter a number between 1 and %d".formatted(CATEGORIES.length + 2);

        while (true) {
            System.out.println(LIST_PURCHASES_MENU);
            String input = scanner.nextLine();
            try {
                int selection = Integer.parseInt(input);
                if (1 <= selection && selection <= CATEGORIES.length)
                    showPurchasesForCategory(CATEGORIES[selection - 1]);
                else if (selection == CATEGORIES.length + 1)
                    showAllPurchases();
                else if (selection == CATEGORIES.length + 2)
                    return;
                else
                    System.out.println(inputErrorMessage);
            } catch (NumberFormatException e) {
                System.out.println(inputErrorMessage);
            }
        }
    }

    void showPurchasesForCategory(Category category) {
        System.out.println("\n" + category + ":");
        if (ledger.hasPurchases(category))
            reports.purchasesForCategory(category);
        else
            System.out.println("The purchase list is empty!");
    }

    void showAllPurchases() {
        System.out.println("\nAll:");
        reports.allPurchases();
    }

    void showBalance() {
        System.out.printf("\nBalance: $%.2f\n", ledger.balance());
    }
}


public class Main {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            Menu menu = new Menu(new Ledger(), in);
            menu.run();
        }
    }
}

record Purchase(Category category, String description, double amount) {}

class Ledger {
    private final List<Purchase> purchases = new ArrayList<>();
    private double balance = 0;

    double balance() {
        return balance;
    }

    Stream<Purchase> purchases() {
        return purchases.stream();
    }

    void addIncome(double amount) {
        balance += amount;
    }

    void addPurchase(Category category, String description, double amount) {
        purchases.add(new Purchase(category, description, amount));
        balance = Math.max(balance - amount, 0);
    }

    boolean hasPurchases() {
        return !purchases.isEmpty();
    }

    boolean hasPurchases(Category category) {
        return purchases.stream().anyMatch(purchase -> purchase.category() == category);
    }
}