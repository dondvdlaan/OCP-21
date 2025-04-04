package dev.manyroads.projects.coffeemachine.stage5.example2;

import java.util.Scanner;

class Main extends MainMenu {
    public static void main(String[] args) {
        selectionOfOperations();
    }
}

class MainMenu extends CoffeeMachine {

    static void selectionOfOperations(){

        while (true) {

            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");

            switch (scanner.nextLine()) {
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    fillSupplies();
                    break;
                case "take":
                    takeMoney();
                    break;
                case "remaining":
                    printState();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Non-existent menu item, try again");
                    break;
            }
        }
    }
}

class CoffeeMachine {

    static Scanner scanner = new Scanner(System.in);

    static void printState() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n%d ml of milk\n%d g of coffee beans\n%d disposable cups\n$%d of money\n\n",
                Resources.WATER.getRemainder(),
                Resources.MILK.getRemainder(),
                Resources.COFFEE_BEANS.getRemainder(),
                Resources.DISPOSABLE_CUPS.getRemainder(),
                Resources.MONEY.getRemainder());
    }

    static void buyCoffee() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");

        String selectedItem = scanner.nextLine();

        if (selectedItem.equals("back")) return;

        if (Integer.parseInt(selectedItem) >= 1 && Integer.parseInt(selectedItem) <= 3) {
            if (Coffee.values()[Integer.parseInt(selectedItem) - 1].getMissingSupplies().isEmpty()) {
                System.out.println("I have enough resources, making you a coffee!");
                Coffee.values()[Integer.parseInt(selectedItem) - 1].makePortion();
            } else {
                System.out.printf("Sorry, not enough %s!\n",
                        Coffee.values()[Integer.parseInt(selectedItem) - 1].getMissingSupplies());
            }
        } else {
            System.out.println("Non-existent menu item, try again");
        }
    }

    static void fillSupplies() {
        String[] userRequest = {"Write how many ml of water you want to add:",
                "Write how many ml of milk you want to add:",
                "Write how many grams of coffee beans you want to add:",
                "Write how many disposable cups of coffee you want to add:"};
        for (int i = 0; i < userRequest.length; i++) {
            System.out.println(userRequest[i]);
            Resources.values()[i].addToRemainder(scanner.nextInt());
        }
    }

    static void takeMoney() {
        System.out.printf("I gave you $%d\n", Resources.MONEY.getRemainder());
        Resources.MONEY.subtractFromRemainder(Resources.MONEY.getRemainder());
    }
}

enum Coffee {
    ESPRESSO(250, 0, 16, 1, 4),
    LATTE(350, 75, 20, 1, 7),
    CAPPUCCINO(200, 100, 12, 1, 6);

    private final int COST;
    private final int[] requiredSupplies;

    Coffee(int WATER, int MILK, int COFFEE_BEAN, int CUP, int COST) {
        this.COST = COST;
        this.requiredSupplies = new int[]{WATER, MILK, COFFEE_BEAN, CUP};
    }

    String getMissingSupplies() {
        for (int i = 0; i < requiredSupplies.length; i++) {
            if (requiredSupplies[i] > Resources.values()[i].getRemainder()) {
                return Resources.values()[i].name().toLowerCase().replace('_', ' ');
            }
        }
        return "";
    }

    void makePortion() {
        for (int i = 0; i < requiredSupplies.length; i++) {
            Resources.values()[i].subtractFromRemainder(requiredSupplies[i]);
        }
        Resources.MONEY.addToRemainder(COST);
    }
}

enum Resources {
    WATER(400),
    MILK(540),
    COFFEE_BEANS(120),
    DISPOSABLE_CUPS(9),
    MONEY(550);

    private int remainder;

    Resources(int remainder) {
        this.remainder = remainder;
    }

    int getRemainder() {
        return this.remainder;
    }

    void subtractFromRemainder(int number) {
        this.remainder -= number;
    }

    void addToRemainder(int number) {
        this.remainder += number;
    }
}
