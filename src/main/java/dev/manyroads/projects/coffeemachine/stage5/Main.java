package dev.manyroads.projects.coffeemachine.stage5;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.System.exit;

/**
 * Handling only a single action at a time is quite limited, so let's improve the program to handle multiple actions, one after another.
 * The program should repeatedly ask a user what they want to do. If the user types "buy", "fill" or "take", then the program should behave
 * exactly as it did in the previous stage. But unlike the previous stage, where the state of the coffee machine was displayed before and
 * after each action ("buy", "fill" or "take"), the state of the coffee machine should now be shown only when the user types "remaining".
 * Additionally, if the user wants to switch off the coffee machine, they should type "exit" to stop the program. In total, the program
 * should now five actions: "buy", "fill", "take", "remaining", and "exit".
 * <p>
 * Remember, that:
 * <p>
 * For a cup of espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
 * For a latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
 * And for a cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans. It costs $6.
 * <p>
 * Objectives
 * Write a program that continuously processes user actions until the "exit" command is given. Additionally, introduce
 * two new options:
 * <p>
 * "remaining": to display the current state of the coffee machine
 * "exit": to switch off the coffee machine
 * Remember, the coffee machine can run out of resources. If it doesn't have enough resources to make coffee, the program
 * should output a message that says it can't make a cup of coffee and indicate which resource is missing.
 * <p>
 * And the last improvement to the program in this stage — if the user types "buy" to buy a cup of coffee but then changes
 * their mind, they should be able to type "back" to return into the main menu.
 * <p>
 * Your coffee machine should start with the same initial resources: 400 ml of water, 540 ml of milk, 120 g of coffee beans,
 * 9 disposable cups, $550 in cash.
 * The program should loop indefinitely, processing actions until the user types "exit" to switch off the coffee machine.
 * Example
 * Write action (buy, fill, take, remaining, exit):
 * > remaining
 * <p>
 * The coffee machine has:
 * 400 ml of water
 * 540 ml of milk
 * 120 g of coffee beans
 * 9 disposable cups
 * $550 of money
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > buy
 * <p>
 * What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:
 * > 2
 * I have enough resources, making you a coffee!
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > remaining
 * <p>
 * The coffee machine has:
 * 50 ml of water
 * 465 ml of milk
 * 100 g of coffee beans
 * 8 disposable cups
 * $557 of money
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > buy
 * <p>
 * What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:
 * > 2
 * Sorry, not enough water!
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > fill
 * <p>
 * Write how many ml of water you want to add:
 * > 1000
 * Write how many ml of milk you want to add:
 * > 0
 * Write how many grams of coffee beans you want to add:
 * > 0
 * Write how many disposable cups you want to add:
 * > 0
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > remaining
 * <p>
 * The coffee machine has:
 * 1050 ml of water
 * 465 ml of milk
 * 100 g of coffee beans
 * 8 disposable cups
 * $557 of money
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > buy
 * <p>
 * What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:
 * > 2
 * I have enough resources, making you a coffee!
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > remaining
 * <p>
 * The coffee machine has:
 * 700 ml of water
 * 390 ml of milk
 * 80 g of coffee beans
 * 7 disposable cups
 * $564 of money
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > take
 * <p>
 * I gave you $564
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > remaining
 * <p>
 * The coffee machine has:
 * 700 ml of water
 * 390 ml of milk
 * 80 g of coffee beans
 * 7 disposable cups
 * $0 of money
 * <p>
 * Write action (buy, fill, take, remaining, exit):
 * > exit
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
            case "remaining" ->
                    System.out.printf(Options.STATE_OF_MACH.getDescription(), water, milk, beans, cups, deposit);
            case "exit" -> exit(0);
            default -> System.out.println("mainMenu: No such option!");
        }
    }

    void buyMenu() {
        String sBuyOption = userInput(Options.BUY_OPTIONS.getDescription()).orElse("0");
        if ("back".equals(sBuyOption)) return;
        if (checkOptions.apply(sBuyOption)) {
            int buyOption = Integer.parseInt(sBuyOption);
            switch (buyOption) {
                case 1 -> processOption(new Expresso(new BigDecimal(4), 250, 0, 16));
                case 2 -> processOption(new Latte(new BigDecimal(7), 350, 78, 20));
                case 3 -> processOption(new Capucciono(new BigDecimal(6), 200, 100, 12));
            }
        } else System.out.println("buyMenu: No such option!");
    }

    Function<String, Boolean> checkOptions = s -> s.charAt(0) >= '1' && s.charAt(0) <= '3';

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
        if (checkResources(typeKoffie)) {
            System.out.println(Options.SUFFICIENT_RESOURCE.getDescription());
            this.deposit = this.deposit.add(typeKoffie.price);
            this.water -= typeKoffie.waterPerCup;
            this.milk -= typeKoffie.milkPerCup;
            this.beans -= typeKoffie.coffeeBeansPerCup;
            this.cups--;
        }
    }

    boolean checkResources(Koffie typeKoffie) {
        String lowOnResource = "";
        if (water < typeKoffie.waterPerCup) lowOnResource += " water";
        if (milk < typeKoffie.milkPerCup) lowOnResource += " milk ";
        if (beans < typeKoffie.coffeeBeansPerCup) lowOnResource += " beans ";
        if (cups < 1) lowOnResource += " cups ";
        if (!lowOnResource.isEmpty())
            System.out.println(String.format(Options.FAILING_RESOURCE.getDescription(), lowOnResource));
        return lowOnResource.isEmpty();
    }

    @Override
    public void run() {
        while (true) {
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
    WRITE_ACTION("Write action (buy, fill, take, remaining, exit): "),
    BUY_OPTIONS("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:"),
    HOW_MANY_CUPS("Write how many disposable cups you want to add: "),
    HOW_MANY_WATER("Write how many ml of water you want to add: "),
    HOW_MANY_MILK("Write how many ml of milk you want to add: "),
    HOW_MANY_BEANS("Write how many grams of coffee beans you want to add: "),
    GAVE_YOU("I gave you %.0f "),
    SUFFICIENT_RESOURCE("I have enough resources, making you a coffee!"),
    FAILING_RESOURCE("Sorry, not enough %s!"),

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
