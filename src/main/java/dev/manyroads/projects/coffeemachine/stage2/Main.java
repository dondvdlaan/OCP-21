package dev.manyroads.projects.coffeemachine.stage2;

import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Now let's consider a scenario where you need a lot of coffee—perhaps you're hosting a party with many guests! In such cases,
 * it's better to make preparations in advance.
 * <p>
 * In this stage, you will ask the user to enter the desired number of coffee cups. Based on this input, you will calculate
 * the necessary amounts of water, coffee, and milk needed to prepare the specified quantity of coffee.
 * <p>
 * Please note that the coffee machine won't actually make any coffee in this stage; instead, it will simply compute the
 * required ingredients.
 * <p>
 * Objectives
 * Let's break down the task into several steps:
 * <p>
 * Read the number of coffee cups from the input.
 * Calculate the amount of each ingredient needed. One cup of coffee requires:
 * 200 ml of water
 * 50 ml of milk
 * 15 g of coffee beans
 * Output the required ingredient amounts back to the user.
 * Example 1: a dialogue with a user might look like this
 * <p>
 * Write how many cups of coffee you will need:
 * > 25
 * For 25 cups of coffee you will need:
 * 5000 ml of water
 * 1250 ml of milk
 * 375 g of coffee beans
 */
public class Main {

    public static void main(String[] args) {
        Machine coffeeMachine = new Machine(200, 50, 15);
        coffeeMachine.run();
    }
}

interface CoffeeMachine {
    int enterCups();
}

class Machine implements CoffeeMachine, Runnable {
    Supplier<Integer> userInput = () -> Integer.parseInt(new Scanner(System.in).nextLine());
    int water;
    int milk;
    int coffeeBeans;

    public Machine(int water, int milk, int coffeBeans) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeBeans;
    }

    @Override
    public int enterCups() {
        System.out.println(Options.HOW_MANY_CUPS.getDescription());
        return userInput.get();
    }

    public void displayIngredients(int cups) {
        System.out.println(String.format(Options.FOR_CUPS_NEED.getDescription(), cups));
        System.out.println(String.format(Options.WATER.getDescription(), cups * water));
        System.out.println(String.format(Options.MILK.getDescription(), cups * milk));
        System.out.println(String.format(Options.BEANS.getDescription(), cups * coffeeBeans));

    }

    @Override
    public void run() {
        int cups = enterCups();
        displayIngredients(cups);
    }
}

enum Options {
    MENU("""
            Starting to make a coffee
            Grinding coffee beans
            Boiling water
            Mixing boiled water with crushed coffee beans
            Pouring coffee into the cup
            Pouring some milk into the cup
            Coffee is ready!
            """),
    HOW_MANY_CUPS("Write how many cups of coffee you will need:"),
    FOR_CUPS_NEED("For %d cups of coffee you will need:"),
    WATER("%d ml of water"),
    MILK("%d ml of milk"),
    BEANS("%d g of coffee beans");

    private String description;

    Options(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
