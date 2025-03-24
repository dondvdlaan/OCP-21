package dev.manyroads.projects.coffeemachine.stage4.trial1;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Now, let's simulate an actual coffee machine! This coffee machine will have a limited supply of water, milk,
 * coffee beans, and disposable cups. Additionally, it will track how much money it earns from selling coffee.
 * <p>
 * The coffee machine will have three main functions:
 * <p>
 * It can sell different types of coffee: espresso, latte, and cappuccino. Of course, each variety would
 * require a different amount of supplies, however, in any case, would need only one disposable cup for a drink.
 * A special worker should be able to replenish the machine's supplies.
 * Another special worker should be able to collect the money earned by the machine.
 * Objectives
 * Write a program that offers three actions: buying coffee, refilling supplies, or taking its money out.
 * Note that the program is supposed to perform only one of the mentioned actions at a time for each input.
 * It should update the coffee machine's state accordingly i.e. calculate the amounts of remaining ingredients
 * and the total money collected; and display them before and after each action.
 * <p>
 * First, your program reads one option from the standard input, which can be "buy", "fill", "take".
 * If a user wants to buy some coffee, the input is "buy". If a special worker thinks that it is time to
 * fill out all the supplies for the coffee machine, the input line will be "fill". If another special worker
 * decides that it is time to take out the money from the coffee machine, you'll get the input "take".
 * If the user writes "buy" then they must choose one of three types of coffee that the coffee machine
 * can make: espresso, latte, or cappuccino.
 * For a cup of espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
 * For a latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
 * And for a cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans. It costs $6.
 * If the user writes "fill", the program should ask them how much water, milk, coffee beans, and how
 * many disposable cups they want to add into the coffee machine.
 * If the user writes "take" the program should give all the money that it earned from selling coffee.
 * In summary, your program should display the coffee machine's current state, process one user action,
 * and then display the updated state. Aim to implement each action using separate functions.
 * <p>
 * When the user writes "buy", they will be prompted to choose a coffee type by entering a number:
 * 1 for espresso, 2 for latte, 3 for cappuccino.
 * Initially, the coffee machine has $550, 400 ml of water, 540 ml of milk, 120 g of coffee beans,
 * and 9 disposable cups.
 * Example
 * The coffee machine has:
 * 400 ml of water
 * 540 ml of milk
 * 120 g of coffee beans
 * 9 disposable cups
 * $550 of money
 * <p>
 * Write action (buy, fill, take):
 * > buy
 * What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:
 * > 3
 * <p>
 * The coffee machine has:
 * 200 ml of water
 * 440 ml of milk
 * 108 g of coffee beans
 * 8 disposable cups
 * $556 of money
 * <p>
 * Example
 * The coffee machine has:
 * 400 ml of water
 * 540 ml of milk
 * 120 g of coffee beans
 * 9 disposable cups
 * $550 of money
 * <p>
 * Write action (buy, fill, take):
 * > fill
 * Write how many ml of water you want to add:
 * > 2000
 * Write how many ml of milk you want to add:
 * > 500
 * Write how many grams of coffee beans you want to add:
 * > 100
 * Write how many disposable cups you want to add:
 * > 10
 * <p>
 * The coffee machine has:
 * 2400 ml of water
 * 1040 ml of milk
 * 220 g of coffee beans
 * 19 disposable cups
 * $550 of money
 * Example
 * The coffee machine has:
 * 400 ml of water
 * 540 ml of milk
 * 120 g of coffee beans
 * 9 disposable cups
 * $550 of money
 * <p>
 * Write action (buy, fill, take):
 * > take
 * I gave you $550
 * <p>
 * The coffee machine has:
 * 400 ml of water
 * 540 ml of milk
 * 120 g of coffee beans
 * 9 disposable cups
 * $0 of money
 */
public class Main {

    public static void main(String[] args) {
        KoffieMachine koffieMachine = new Machine(new BigDecimal(550), 400, 540, 120, 9);
        koffieMachine.run();
    }
}

interface KoffieMachine extends Runnable, Supplier<Optional<String>> {
    Optional<String> userInput(String message);
}

class Machine implements KoffieMachine {
    //Supplier<String> scanInput = () -> new Scanner(System.in).nextLine();
    BigDecimal deposit;
    int water;
    int milk;
    int beans;
    int cups;

    public Machine(BigDecimal deposit, int water, int milk, int beans, int cups) {
        this.deposit = deposit;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
    }

    @Override
    public Optional<String> userInput(String message) {
        System.out.println(message);
        return get();
    }

    public void displayStateMachine() {
        System.out.println(String.format(Options.FOR_CUPS_NEED.getDescription(), cups));
        System.out.println(String.format(Options.WATER.getDescription(), cups * water));
        System.out.println(String.format(Options.MILK.getDescription(), cups * milk));
        System.out.println(String.format(Options.BEANS.getDescription(), cups * beans));
    }

    private void calculateAndDisplayResult(int waterAvail, int milkAvail, int beansAvail, int cupsReq) {
        int cupsAvail = Stream.of(waterAvail / water, milkAvail / milk, beansAvail / beans)
                .min(Integer::compare).orElse(0);
        int cupsExtra = cupsAvail - cupsReq;
        if (cupsAvail >= cupsReq)
            System.out.println(String.format(Options.CUPS_AVAIL_EXTRA.getDescription(), cupsExtra));
        if (cupsAvail == cupsReq) System.out.println(Options.CUPS_AVAIL_EXACT.getDescription());
        if (cupsAvail < cupsReq) System.out.println(String.format(Options.CUPS_MIN_AVAIL.getDescription(), cupsAvail));
    }

    void mainMenu() {
        String action = userInput(Options.WRITE_ACTION.getDescription()).orElse("");
        switch (action) {
            case "buy" -> buyMenu();
            case "fill" -> fillMenu();
            case "take" -> takeMenu();
            default -> System.out.println("mainMenu: No such option!");
        }
    }

    void buyMenu() {
        try {
            int buyOption = Integer.parseInt(userInput(Options.BUY_OPTIONS.getDescription()).orElse("0"));
            switch (buyOption) {
                case 1 -> processOption(new Expresso(new BigDecimal(4), 250, 0, 16));
                case 2 -> processOption(new Latte(new BigDecimal(7), 350, 78, 20));
                case 3 -> processOption(new Capucciono(new BigDecimal(6), 200, 100, 12));
            }
        } catch (NumberFormatException ex) {
            System.out.println("buyMenu: No such option!");
        }
    }

    void fillMenu() {
        this.water += Integer.parseInt(userInput(Options.HOW_MANY_WATER.getDescription()).orElse("0"));
        this.milk += Integer.parseInt(userInput(Options.HOW_MANY_MILK.getDescription()).orElse("0"));
        this.beans += Integer.parseInt(userInput(Options.HOW_MANY_BEANS.getDescription()).orElse("0"));
        this.cups += Integer.parseInt(userInput(Options.HOW_MANY_CUPS.getDescription()).orElse("0"));
    }

    void takeMenu() {
        System.out.println(String.format(Options.GAVE_YOU.getDescription(), this.deposit));
        this.deposit = new BigDecimal(0);
    }

    void processOption(Koffie typeKoffie) {
        this.deposit = this.deposit.add(typeKoffie.price);
        this.water -= typeKoffie.waterPerCup;
        this.milk -= typeKoffie.milkPerCup;
        this.beans -= typeKoffie.coffeeBeansPerCup;
        this.cups--;
    }

    @Override
    public void run() {
        while (true) {
            System.out.printf(Options.STATE_OF_MACH.getDescription(), water, milk, beans, cups, deposit);
            mainMenu();
        }
    }

    @Override
    public Optional<String> get() {
        return Optional.ofNullable(new Scanner(System.in).nextLine());
    }
}

enum Options {
    STATE_OF_MACH("""
            The coffee machine has:
            %d ml of water
            %d ml of milk
            %d g of coffee beans
            %d disposable cups
            $%.0f of money
            """),
    WRITE_ACTION("Write action (buy, fill, take): "),
    BUY_OPTIONS("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:"),
    HOW_MANY_CUPS("Write how many disposable cups you want to add: "),
    HOW_MANY_WATER("Write how many ml of water you want to add: "),
    HOW_MANY_MILK("Write how many ml of milk you want to add: "),
    HOW_MANY_BEANS("Write how many grams of coffee beans you want to add: "),
    GAVE_YOU("I gave you %.0f "),

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

abstract class Koffie {
    BigDecimal price;
    int waterPerCup;
    int milkPerCup;
    int coffeeBeansPerCup;

    public Koffie(BigDecimal price, int waterPerCup, int milkPerCup, int coffeeBeansPerCup) {
        this.price = price;
        this.waterPerCup = waterPerCup;
        this.milkPerCup = milkPerCup;
        this.coffeeBeansPerCup = coffeeBeansPerCup;
    }
}

class Expresso extends Koffie {

    public Expresso(BigDecimal price, int waterPerCup, int milkPerCup, int coffeeBeansPerCup) {
        super(price, waterPerCup, milkPerCup, coffeeBeansPerCup);
    }
}

class Latte extends Koffie {

    public Latte(BigDecimal price, int waterPerCup, int milkPerCup, int coffeeBeansPerCup) {
        super(price, waterPerCup, milkPerCup, coffeeBeansPerCup);
    }
}

class Capucciono extends Koffie {

    public Capucciono(BigDecimal price, int waterPerCup, int milkPerCup, int coffeeBeansPerCup) {
        super(price, waterPerCup, milkPerCup, coffeeBeansPerCup);
    }
}
