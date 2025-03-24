package dev.manyroads.projects.coffeemachine.stage5.example;

import java.util.Locale;
import java.util.Scanner;

public class CoffeeMachine {

    private static final Scanner scanner = new Scanner(System.in);
    private static int water = 400;
    private static int milk = 540;
    private static int beans = 120;
    private static int cups = 9;
    private static int money = 550;

    public static void main(String[] args) {
        action();
    }

    public static void howMuch() {
        System.out.println("The coffee machine has: ");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
        System.out.println();
        action();
    }

    public static void action() {
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        String input = scanner.next();

        switch (input.toLowerCase(Locale.ROOT)) {
            case "take":
                take();
                break;
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "remaining":
                howMuch();
                break;
            case "exit":
                System.exit(0);
                break;
        }
    }

    public static void take() {
        System.out.println("I gave you $" + money);
        money = 0;
        action();
    }

    public static void fill() {
        System.out.println("Write how many ml of water you want to add: ");
        int input = scanner.nextInt();
        water += input;
        System.out.println("Write how many ml of milk you want to add: ");
        input = scanner.nextInt();
        milk += input;
        System.out.println("Write how many grams of coffee beans you want to add: ");
        input = scanner.nextInt();
        beans += input;
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        input = scanner.nextInt();
        cups += input;
        action();
    }

    public static void buy() {
        System.out.println();
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String input = scanner.next();

        switch (input) {
            case "1":
                coffeeMaking(CoffeeStandards.ESSPRESSO);
                break;
            case "2":
                coffeeMaking(CoffeeStandards.LATTE);
                break;
            case "3":
                coffeeMaking(CoffeeStandards.CAPPUCCINO);
                break;
            case "back":
                action();
                break;

        }
    }

    public static void coffeeMaking(CoffeeStandards coffeeStandards) {
        boolean isWater = water >= coffeeStandards.getWater();
        boolean isMilk = milk >= coffeeStandards.getMilk();
        boolean isBeans = beans >= coffeeStandards.getBeans();
        boolean isCups = cups > 0;
        if (isWater && isMilk && isBeans && isCups) {
            water -= coffeeStandards.getWater();
            milk -= coffeeStandards.getMilk();
            beans -= coffeeStandards.getBeans();
            money += coffeeStandards.getPrice();
            cups -= 1;
            System.out.println("I have enough resources, making you a coffee!");

            action();
        } else {
            if (!isBeans) {
                System.out.println("Sorry, not enough beans!");
                action();
            } else if (!isWater) {
                System.out.println("Sorry, not enough water!");
                action();
            } else if (!isMilk) {
                System.out.println("Sorry, not enough milk!");
                action();
            } else if (!isCups) {
                System.out.println("Sorry, not enough cups!");
                action();
            }
        }

    }
}
