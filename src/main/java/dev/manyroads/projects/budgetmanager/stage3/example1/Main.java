package dev.manyroads.projects.budgetmanager.stage3.example1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

class Menu implements Runnable {
    private static final Scanner scanner = new Scanner(System.in);

    private final Map<String, Entry> map = new LinkedHashMap<>();
    private final ResourceBundle bundle;

    private final String title;
    private boolean once;
    private String format = "%s) %s%n";

    public Menu(final String title) {
        this.title = title;
        bundle = null;
    }

    public Menu(final ResourceBundle bundle) {
        this.bundle = bundle;
        this.title = bundle.getString("title");
        this.format = bundle.getString("line-format");
    }

    public Menu once() {
        once = true;
        return this;
    }

    public Menu setFormat(String pattern) {
        format = pattern;
        return this;
    }

    public Menu add(final String key, final String description, final Runnable action) {
        map.put(key, new Entry(description, action));
        return this;
    }

    public Menu add(final String description, final Runnable action) {
        return add(String.valueOf(map.size() + 1), description, action);
    }

    public Menu addExit() {
        return add("0", "Exit", this::once);
    }

    @Override
    public void run() {
        do {
            System.out.println();
            System.out.println(title);
            map.forEach((key, entry) -> System.out.printf(format, key, entry));
            final var key = scanner.nextLine().toLowerCase();
            System.out.println();
            map.getOrDefault(key, new Entry("Error", this::printErrorMessage)).run();
        } while (!once);
    }

    private void printErrorMessage() {
        final var msg = nonNull(bundle)
                ? bundle.getString("error")
                : "Please enter the number from 0 up to {0}";
        System.out.println(MessageFormat.format(msg, map.size()));
    }

    private static final class Entry implements Runnable {
        private final String description;
        private final Runnable action;

        Entry(final String name, final Runnable action) {
            this.description = name;
            this.action = action;
        }

        @Override
        public String toString() {
            return description;
        }

        @Override
        public void run() {
            action.run();
        }
    }
}


class Account {
    private final List<Purchase> history;
    private BigDecimal balance;

    public Account() {
        balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        history = new LinkedList<>();
    }

    public void addIncome(final BigDecimal income) {
        balance = balance.add(income);
    }

    public void addPurchase(final Purchase purchase) {
        balance = balance.subtract(purchase.getPrice());
        history.add(purchase);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Purchase> getHistory() {
        return history;
    }
}

 class Application implements Runnable {
    private final Scanner scanner;
    private final Account account;

    public Application(Account account) {
        scanner = new Scanner(System.in);
        this.account = account;
    }

    @Override
    public void run() {

        new Menu("Choose your action:")
                .add("Add income", this::addIncome)
                .add("Add purchase", getCategoryMenu(this::addPurchase, false))
                .add("Show list of purchases", this::showPurchases)
                .add("Balance", this::printBalance)
                .addExit()
                .run();

        System.out.println("Bye!");
    }

    private void addIncome() {
        System.out.println("Enter income:");
        account.addIncome(new BigDecimal(scanner.nextLine()));
    }

    private void printBalance() {
        System.out.println("Balance: $" + account.getBalance());
    }

    private void addPurchase(final Purchase.Category category) {
        System.out.println("Enter purchase name:");
        final var name = scanner.nextLine();
        System.out.println("Enter its price:");
        final var price = new BigDecimal(scanner.nextLine());
        account.addPurchase(new Purchase(category, name, price));
        System.out.println("Purchase was added!");
    }

    private void printListEmpty() {
        System.out.println("Purchase list is empty!");
    }

    private void showPurchases() {
        if (account.getHistory().size() == 0) {
            printListEmpty();
        } else {
            getCategoryMenu(this::showPurchases, true).run();
        }
    }

    private Menu getCategoryMenu(final Consumer<Purchase.Category> action, final boolean isAll) {
        final var menu = new Menu("Choose the type of purchase");
        Arrays.stream(Purchase.Category.values())
                .forEach(category -> menu.add(category.name(), () -> action.accept(category)));
        if (isAll) {
            menu.add("All", () -> action.accept(null));
        }
        menu.add("Back", menu::once);
        return menu;
    }

    private void showPurchases(final Purchase.Category category) {
        System.out.println(isNull(category) ? "All" : category.name());
        account.getHistory()
                .stream()
                .filter(purchase -> isNull(category) || purchase.getCategory() == category)
                .peek(System.out::println)
                .map(Purchase::getPrice)
                .reduce(BigDecimal::add)
                .ifPresentOrElse(
                        total -> System.out.println("Total sum: $" + total),
                        this::printListEmpty);
    }
}

class Purchase {
    private final Category category;
    private final String description;
    private final BigDecimal price;

    public Purchase(
            final Category category,
            final String description,
            final BigDecimal price) {
        this.category = category;
        this.description = description;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s $%s", description, price);
    }

    public enum Category {
        FOOD, CLOTHES, ENTERTAINMENT, OTHER
    }

}
