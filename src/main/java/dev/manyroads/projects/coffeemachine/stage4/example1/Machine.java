package dev.manyroads.projects.coffeemachine.stage4.example1;

import java.util.ArrayList;
import java.util.Scanner;

public class Machine {
    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int money;
    private ArrayList<Receipt> receipts;

    public Machine(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;
        initReceipt();
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getCups() {
        return cups;
    }

    public int getMoney() {
        return money;
    }

    protected void initReceipt() {
        receipts = new ArrayList<Receipt>();
        receipts.add(new Receipt(1, "espresso", 250, 0, 16, 4));
        receipts.add(new Receipt(2, "latte", 350, 75, 20, 7));
        receipts.add(new Receipt(3, "cappuccino", 200, 100, 12, 6));
    }

    public void status() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(String.format("%d of water", water));
        System.out.println(String.format("%d of milk", milk));
        System.out.println(String.format("%d of coffee beans", coffee));
        System.out.println(String.format("%d of disposable cups", cups));
        System.out.println(String.format("%d of money", money));
    }

    public void processAction() {
        System.out.println("Write action (buy, fill, take): ");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        if (action.equalsIgnoreCase("buy")) {
            processBuy();
        } else if (action.equalsIgnoreCase("fill")) {
            processFill();
        } else if (action.equalsIgnoreCase("take")) {
            processTake();
        } else {
            System.out.println("Unknown command");
        }
    }

    void processBuy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        Scanner scanner = new Scanner(System.in);
        int receiptId = scanner.nextInt();
        makeCoffee(receiptId);
    }

    void processTake() {
        System.out.println(String.format("I gave you $%d", money));
        money = 0;
    }

    void processFill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffee += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += scanner.nextInt();
    }

    private void makeCoffee(int receiptId) {
        Receipt receipt =
                receipts
                        .stream()
                        .filter(n -> n.getId() == receiptId)
                        .findFirst().orElseThrow(IllegalArgumentException::new);
        if (water >= receipt.getWater() &&
                milk >= receipt.getMilk() &&
                coffee >= receipt.getCoffee() &&
                cups >= 1) {
            water -= receipt.getWater();
            milk -= receipt.getMilk();
            coffee -= receipt.getCoffee();
            money += receipt.getCost();
            cups -= 1;
        } else {
            System.out.println("Sorry, haven't ingredients");
        }
    }
}
