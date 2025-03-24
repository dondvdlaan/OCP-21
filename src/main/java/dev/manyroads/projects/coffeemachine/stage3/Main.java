package dev.manyroads.projects.coffeemachine.stage3;

import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * A real coffee machine doesn't have an infinite supply of water, milk, or coffee beans. If you request too many cups of coffee,
 * it's almost certain that a real coffee machine wouldn't have enough supplies to fulfill the order.
 * <p>
 * In this stage, you need to improve the previous stage program. Now you will check amounts of water, milk, and coffee beans
 * available in the coffee machine at the moment.
 * <p>
 * Objectives
 * Write a program that does the following:
 * <p>
 * Requests the amounts of water, milk, and coffee beans available at the moment, and then asks for the number of
 * cups of coffee a user needs.
 * If the coffee machine has enough supplies to make the specified amount of coffee, the program should print
 * "Yes, I can make that amount of coffee".
 * If the coffee machine can make more than the requested amount, the program should output "Yes, I can make
 * that amount of coffee (and even N more than that)", where N is the number of additional cups of coffee that
 * the coffee machine can make.
 * If the available resources are insufficient to make the requested amount of coffee, the program should output
 * "No, I can make only N cup(s) of coffee".
 * Like in the previous stage, the coffee machine needs 200 ml of water, 50 ml of milk, and 15 g of coffee beans
 * to make one cup of coffee.
 * Example 1:
 * <p>
 * Write how many ml of water the coffee machine has:
 * > 300
 * Write how many ml of milk the coffee machine has:
 * > 65
 * Write how many grams of coffee beans the coffee machine has:
 * > 100
 * Write how many cups of coffee you will need:
 * > 1
 * Yes, I can make that amount of coffee
 */
public class Main {

    public static void main(String[] args) {
        Machine coffeeMachine = new Machine(200, 50, 15);
        coffeeMachine.run();
    }
}

interface KoffieMachine {
    int userInput(String message);
}

class Machine implements KoffieMachine, Runnable {
    Supplier<Integer> userInput = () -> Integer.parseInt(new Scanner(System.in).nextLine());
    int waterPerCup;
    int milkPerCup;
    int coffeeBeansPerCup;

    public Machine(int waterPerCup, int milkPerCup, int coffeeBeansPerCup) {
        this.waterPerCup = waterPerCup;
        this.milkPerCup = milkPerCup;
        this.coffeeBeansPerCup = coffeeBeansPerCup;
    }

    @Override
    public int userInput(String message) {
        System.out.println(message);
        return userInput.get();
    }

    public void displayIngredients(int cups) {
        System.out.println(String.format(Options.FOR_CUPS_NEED.getDescription(), cups));
        System.out.println(String.format(Options.WATER.getDescription(), cups * waterPerCup));
        System.out.println(String.format(Options.MILK.getDescription(), cups * milkPerCup));
        System.out.println(String.format(Options.BEANS.getDescription(), cups * coffeeBeansPerCup));
    }

    private void calculateAndDisplayResult(int waterAvail, int milkAvail, int beansAvail, int cupsReq) {
        int cupsAvail = Stream.of(waterAvail / waterPerCup, milkAvail / milkPerCup, beansAvail / coffeeBeansPerCup)
                .min(Integer::compare).orElse(0);
        int cupsExtra = cupsAvail - cupsReq;
        if (cupsAvail >= cupsReq)
            System.out.println(String.format(Options.CUPS_AVAIL_EXTRA.getDescription(), cupsExtra));
        if (cupsAvail == cupsReq) System.out.println(Options.CUPS_AVAIL_EXACT.getDescription());
        if (cupsAvail < cupsReq) System.out.println(String.format(Options.CUPS_MIN_AVAIL.getDescription(), cupsAvail));
    }

    @Override
    public void run() {
        int waterAvail = userInput(Options.HOW_MANY_WATER.getDescription());
        int milkAvail = userInput(Options.HOW_MANY_MILK.getDescription());
        int beansAvail = userInput(Options.HOW_MANY_BEANS.getDescription());
        int cupsReq = userInput(Options.HOW_MANY_CUPS.getDescription());
        calculateAndDisplayResult(waterAvail, milkAvail, beansAvail, cupsReq);
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
    HOW_MANY_WATER("Write how many ml of water the coffee machine has:"),
    HOW_MANY_MILK("Write how many ml of milk the coffee machine has:"),
    HOW_MANY_BEANS("Write how many grams of coffee beans the coffee machine has:"),
    FOR_CUPS_NEED("For %d cups of coffee you will need:"),
    CUPS_AVAIL_EXACT("Yes, I can make that amount of coffee"),
    CUPS_AVAIL_EXTRA("Yes, I can make that amount of coffee (and even %d more than that)"),
    CUPS_MIN_AVAIL("No, I can make only %d cup(s) of coffee"),
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
