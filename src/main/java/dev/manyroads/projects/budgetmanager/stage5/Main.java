package dev.manyroads.projects.budgetmanager.stage5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * STAGE 5
 * First, add the Analyze item to the menu. This way you will request an analysis of your purchases.
 * Once this item is called you need to offer a way to sort the purchases.
 * There are three of them:
 * Sort All – sort the entire shopping list and display it so that the most expensive purchases are at the top of the list.
 * Sort By Type – show which category eats the most money. If a category has no purchases in it the total sum should be $0.
 * Sort Certain Type – same as Sort All, but for a specific category.
 */

public class Main {
    public static void main(String[] args) {
        new BudgetManager().start();
    }
}

class BudgetManager {
    private BudgetManagerService service;

    public BudgetManager() {
        this.service = new BudgetManagerService();
    }

    void start() {
        while (true) {
            switch (service.menu(UI.Menus.MAIN.getMenu())) {
                case 1 -> service.addIncomeToBalance();
                case 2 -> addPurchaseMenu();
                case 3 -> showPurchaseMenu();
                case 4 -> service.showBalance();
                case 5 -> service.savePurchases();
                case 6 -> service.loadPurchases();
                case 7 -> sortMenu();
                case 0 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> System.out.println("Wrong selection");
            }
        }
    }

    void addPurchaseMenu() {
        boolean menuOpen = true;
        while (menuOpen) {
            switch (service.menu(UI.Menus.ADD_PURCHASE.getMenu())) {
                case 1 -> service.addPurchaseToCart(Category.FOOD);
                case 2 -> service.addPurchaseToCart(Category.CLOTHES);
                case 3 -> service.addPurchaseToCart(Category.ENTERTAINMENT);
                case 4 -> service.addPurchaseToCart(Category.OTHER);
                case 5 -> menuOpen = false;
                default -> System.out.println("Wrong selection");
            }
        }
    }

    void showPurchaseMenu() {
        boolean menuOpen = true;
        while (menuOpen) {
            switch (service.menu(UI.Menus.SHOW_PURCHASE.getMenu())) {
                case 1 -> service.showPurchases(Category.FOOD);
                case 2 -> service.showPurchases(Category.CLOTHES);
                case 3 -> service.showPurchases(Category.ENTERTAINMENT);
                case 4 -> service.showPurchases(Category.OTHER);
                case 5 -> service.showPurchases(Category.ALL);
                case 6 -> menuOpen = false;
                default -> System.out.println("Wrong selection");
            }
        }
    }

    void sortMenu() {
        boolean menuOpen = true;
        while (menuOpen) {
            switch (service.menu(UI.Menus.SORT.getMenu())) {
                case 1 -> service.analyseBudget(new SortAll());
                case 2 -> service.analyseBudget(new SortByType());
                case 3 -> sortCertainTypeMenu();
                case 4 -> menuOpen = false;
                default -> System.out.println("Wrong selection");
            }
        }
    }

    void sortCertainTypeMenu() {
        switch (service.menu(UI.Menus.SORT_CERTAIN_TYPE.getMenu())) {
            case 1 -> service.analyseBudget(new SortCertainType(Category.FOOD));
            case 2 -> service.analyseBudget(new SortCertainType(Category.CLOTHES));
            case 3 -> service.analyseBudget(new SortCertainType(Category.ENTERTAINMENT));
            case 4 -> service.analyseBudget(new SortCertainType(Category.OTHER));
            default -> System.out.println("Wrong selection");
        }
    }
}

class BudgetManagerService {

    private Account account;
    private Cart cart;
    private UI ui;

    public BudgetManagerService() {
        this.account = new Account();
        this.cart = Cart.getInstance();
        this.ui = new UI();
    }

    int menu(String menu) {
        return ui.menu(menu);
    }

    void addIncomeToBalance() {
        double income = ui.addIncome();
        account.addToBalance(income);
    }

    void addPurchaseToCart(Category category) {
        Product newProduct = ui.addPurchase();
        newProduct.setCategory(category);
        cart.addProductToCart(newProduct);
        account.deductBalance(newProduct.getPrice());
    }

    void showPurchases(Category category) {
        cart.listByProductAndTotal(category);
    }

    void showBalance() {
        System.out.println(account);
    }

    void savePurchases() {
        cart.saveProductsToFile();
    }

    void loadPurchases() {
        cart.loadProductsFromFile();
    }

    void analyseBudget(SortType sortType) {
        if (cart.getProducts().isEmpty()) throw new RuntimeException("Product not available!");
        new Analyse(sortType).sorting(cart.getProducts().get());
    }
}

enum Category {
    FOOD("Food"),
    CLOTHES("Clothes"),
    ENTERTAINMENT("Entertainment"),
    OTHER("Other"),
    ALL("All");

    private final String desc;

    Category(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

class Product {
    Category category;
    String brand;
    double price;

    public Product(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public Product(Category category, String brand, double price) {
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", brand, price);
    }
}

class UI {
    private Scanner sc = new Scanner(System.in).useDelimiter("\\n");

    public enum Menus {
        MAIN("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                7) Analyze (Sort)
                0) Exit
                """),
        ADD_PURCHASE("""
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back
                """),
        SHOW_PURCHASE("""
                Choose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back
                """),
        SORT("""
                How do you want to sort?
                1) Sort all purchases
                2) Sort by type
                3) Sort certain type
                4) Back
                """),
        SORT_CERTAIN_TYPE("""
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                """);
        private final String menu;

        public String getMenu() {
            return menu;
        }

        Menus(String menu) {
            this.menu = menu;
        }
    }

    int menu(String menu) {
        System.out.println();
        System.out.println(menu);
        System.out.println();
        return sc.nextInt();
    }

    double addIncome() {
        System.out.println("Enter income:");
        double income = sc.nextDouble();
        System.out.println("Income was added!\n");
        return income;
    }

    Product addPurchase() {
        System.out.println("Enter purchase name:");
        String desc = sc.next();
        System.out.println("Enter its price:");
        double price = sc.nextDouble();
        System.out.println("Purchase was added!\n");
        return new Product(desc, price);
    }
}

class Account {
    private double balance;

    public Account() {
        this.balance = 0D;
    }

    public double getBalance() {
        return balance;
    }

    void addToBalance(double income) {
        this.balance += income;
    }

    void deductBalance(double purchase) {
        this.balance = (balance - purchase) >= 0 ? balance -= purchase : 0D;
    }

    @Override
    public String toString() {
        return String.format("\nBalance: $%.2f\n", balance);
    }
}

class Cart {
    private static Cart instance;
    private List<Product> products;
    private static final String FILE_NAME = "purchases.txt";

    private Cart() {
        this.products = new ArrayList<>();
    }

    static Cart getInstance() {
        if (instance == null) {
            return new Cart();
        }
        return instance;
    }

    public Optional<List<Product>> getProducts() {
        return Optional.of(this.products);
    }

    public void setProducts(List<Product> products) {
        this.products = products != null ? products : this.products;
    }

    void addProductToCart(Product newProduct) {
        this.products.add(newProduct);
    }

    void saveProductsToFile() {
        File file = new File(FILE_NAME);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            products.forEach(
                    p -> printWriter.printf("%s,%s,%f\n", p.getCategory(), p.getBrand(), p.getPrice()));
            System.out.println("\nPurchases were saved!\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void loadProductsFromFile() {
        File file = new File(FILE_NAME);
        try (Scanner sc = new Scanner(file).useDelimiter("[,\\n]")) {
            while (sc.hasNext()) {
                products.add(new Product(Category.valueOf(sc.next()), sc.next(), sc.nextDouble()));
            }
            System.out.println("\nPurchases were loaded!\n");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void printTotal(List<Product> products) {
        double tot = products.stream().map(Product::getPrice).reduce(Double::sum).orElse(0D);
        System.out.printf("Total sum: $%.2f\n\n", tot);
    }

    void listByProductAndTotal(Category category) {
        Predicate<Product> checkPurchaseCat = p -> category == Category.ALL || p.getCategory() == category;

        if (getProducts().isPresent()) {
            List<Product> productList = getProducts().get().stream().filter(checkPurchaseCat).toList();
            if (productList.isEmpty()) {
                System.out.println("The purchase list is empty\n");
                return;
            } else {
                System.out.println(category.getDesc() + ":");
                productList.forEach(System.out::println);
                System.out.println();
                printTotal(productList);
            }
        }
    }
}

interface SortType {
    void sort(List<Product> elements);
}

class Analyse {

    SortType sortType;

    public Analyse(SortType sortType) {
        this.sortType = sortType;
    }

    void sorting(List<Product> elements) {
        sortType.sort(elements);
    }
}

class SortAll implements SortType {

    @Override
    public void sort(List<Product> products) {
        Comparator<Product> comparePrices = (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice());
        List<Product> sortedProducts = products.stream()
                .sorted(comparePrices).toList().reversed();
        //.sorted(Comparator.comparingDouble(Product::getPrice)).toList().reversed();
        if (sortedProducts.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
            return;
        } else {
            System.out.println(Category.ALL + ":");
            sortedProducts.forEach(System.out::println);
            System.out.println();
            Cart.printTotal(sortedProducts);
        }
    }
}

class SortByType implements SortType {
    @Override
    public void sort(List<Product> products) {
        Map<Category, Double> mapCatTot = new HashMap<>();

        for (Category cat : Category.values()) {
            double tot = products.stream()
                    .filter(p -> p.getCategory() == cat)
                    .map(Product::getPrice)
                    .reduce(Double::sum).orElse(0D);
            mapCatTot.put(cat, tot);
        }
        System.out.println("Types:");
        mapCatTot.entrySet().stream()
                .filter(m -> m.getKey() != Category.ALL)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(m -> System.out.printf("%s - $%.2f\n", m.getKey(), m.getValue()));
        Cart.printTotal(products);
    }
}

class SortCertainType implements SortType {
    Category category;

    public SortCertainType(Category category) {
        this.category = category;
    }

    @Override
    public void sort(List<Product> products) {
        List<Product> sortedProducts = products.stream()
                .filter(p -> p.category == category)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .toList();
        if (sortedProducts.isEmpty()) {
            System.out.println("The purchase list is empty!");
            return;
        } else {
            System.out.println(category.getDesc() + ":");
            sortedProducts.forEach(System.out::println);
            System.out.println();
            Cart.printTotal(sortedProducts);
        }
    }
}
