package dev.manyroads.projects.budgetmanager.stage4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * STAGE 4
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
        cart.listProductsAndTotal(category);
    }

    void showBalance() {
        System.out.println(account);
    }

    void savePurchases() {
        //cart.saveProductsToFileXML();
        //cart.saveProductsToFile();
    }

    void loadPurchases() {
        cart.loadProductsFromFile();
    }
}

enum Category {
    FOOD, CLOTHES, ENTERTAINMENT, OTHER, ALL
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
    private Scanner sc = new Scanner(System.in);

    public enum Menus {
        MAIN("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
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
    //private static final ObjectMapper MAPPER = new XmlMapper();

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
                    p -> printWriter.printf("%s %s %f\n", p.getCategory(), p.getBrand(), p.getPrice()));
            System.out.println("\nPurchases were saved!\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    void saveProductsToFileXML() {
//        File file = new File(FILE_NAME);
//        try  {
//            MAPPER.writeValue(file,this);
//            System.out.println("\nPurchases were saved!\n");
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    void loadProductsFromFile() {
        File file = new File(FILE_NAME);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                products.add(new Product(Category.valueOf(sc.next()), sc.next(), sc.nextDouble()));
            }
            System.out.println("\nPurchases were loaded!\n");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void listProductsAndTotal(Category category) {
        Predicate<Product> checkPurchaseCat = p -> category == Category.ALL || p.getCategory() == category;

        if (getProducts().isPresent()) {
            List<Product> productList = getProducts().get().stream().filter(checkPurchaseCat).toList();
            if (productList.isEmpty()) {
                System.out.println("The purchase list is empty\n");
                return;
            } else {
                productList.forEach(System.out::println);
                System.out.println();
                double tot = productList.stream().map(Product::getPrice).reduce(Double::sum).orElse(0D);
                System.out.printf("Total: $%.2f\n\n", tot);
            }
        }
    }
}