package dev.manyroads.projects.coffeemachine.stage6;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.lang.System.exit;

/**
 * In this stage, let's improve the design of our program by organizing it into classes that represent different parts of
 * the coffee machine. For instance, we can create one class for the coffee machine itself and another class to represent
 * each type of coffee with its ingredients and cost. This approach helps structure the code better, allowing for easier
 * reuse and extension. Each class should have methods that handle specific tasks, working together to process the user
 * input and manage the coffee machine's operations.
 * <p>
 * dditionally, we'll introduce a new action: cleaning. The coffee machine will monitor how many coffees have been made.
 * After producing 10 cups, it will require cleaning. During this action, the machine will not be able to make any more
 * coffee until it is cleaned by the user typing "clean". After cleaning, the machine resumes its normal operations.
 * <p>
 * Your final task is to refactor the program to ensure you can interact with the coffee machine through methods in the
 * classes you created. Implement the cleaning action, where the machine requires cleaning after 10 cups of coffee are made.
 * Once cleaned, the machine can make coffee again.
 */
public class Main {

    public static void main(String[] args) {
        KoffieMachine koffieMachine = new Machine(new BigDecimal(550), 400, 540, 120, 9, 10);
        koffieMachine.run();
    }
}

interface KoffieMachine extends Runnable, Supplier<Optional<String>> {
    Optional<String> userInput(String message);
}

class Machine implements KoffieMachine {
    BigDecimal deposit;
    int water;
    int milk;
    int beans;
    int cups;
    int cupsProduced;
    int maxCupsBeforeCleaning;

    public Machine(BigDecimal deposit, int water, int milk, int beans, int cups, int maxCupsBeforeCleaning) {
        this.deposit = deposit;
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.maxCupsBeforeCleaning = maxCupsBeforeCleaning;
    }

    @Override
    public Optional<String> userInput(String message) {
        System.out.println(message);
        return get();
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
        checkCleaning();
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
            this.cupsProduced++;
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

    void checkCleaning() {
        if (cupsProduced >= maxCupsBeforeCleaning) {
            while (!"clean".equals(userInput(Options.NEED_CLEANING.getDescription()).orElse(""))) {
            }
            cupsProduced = 0;
            System.out.println(Options.DONE_CLEANING.getDescription());
        }
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

    NEED_CLEANING("I need cleaning!"),
    DONE_CLEANING("I have been cleaned!"),

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
