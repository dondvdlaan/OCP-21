package dev.manyroads.projects.budgetmanager.stage3;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * To better control the expenses, we need to categorize our purchases. It helps to see how exactly your budget is
 * distributed: you may be actually quite surprised!
 * Implement a function that assigns a purchase to a specific category.
 * The program should have the following categories:
 * Food
 * Clothes
 * Entertainment
 * Other
 * The function allows you to output the shopping list by type. After selecting the action of showing the list of expenses,
 * offer to show either a certain category or a general list. At the end of each list, print the total sum of purchases
 * that are on the list.
 */

// Alternative
//use "Map<Category, List<Expense>>" instead of declaring a list variable for each category.
//Where "Category" is and enum.
//enum Category {FOOD, CLOTHES, ENTERTAINMENT, OTHER}

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
            switch (service.menu()) {
                case 1 -> service.addIncomeToBalance();
                case 2 -> addPurchaseMenu();
                case 3 -> showPurchaseMenu();
                case 4 -> service.showBalance();
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
            switch (service.addPurchaseMenu()) {
                case 1 -> service.addPurchaseToCart(new Food());
                case 2 -> service.addPurchaseToCart(new Clothes());
                case 3 -> service.addPurchaseToCart(new Entertainment());
                case 4 -> service.addPurchaseToCart(new Other());
                case 5 -> menuOpen = false;
                default -> System.out.println("Wrong selection");
            }
        }
    }

    void showPurchaseMenu() {
        boolean menuOpen = true;
        while (menuOpen) {
            switch (service.showPurchaseMenu()) {
                case 1 -> service.showPurchases(new Food());
                case 2 -> service.showPurchases(new Clothes());
                case 3 -> service.showPurchases(new Entertainment());
                case 4 -> service.showPurchases(new Other());
                case 5 -> service.showPurchases(new Product());
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

    int menu() {
        return ui.mainMenu();
    }

    int addPurchaseMenu() {
        return ui.addPurchaseMenu();
    }

    int showPurchaseMenu() {
        return ui.showPurchaseMenu();
    }

    void addIncomeToBalance() {
        double income = ui.addIncome();
        account.addToBalance(income);
    }

    void addPurchaseToCart(Product newProduct) {
        Product temp = ui.addPurchase(newProduct);
        cart.addProductToCart(temp);
        account.deductBalance(temp.getPrice());
    }

    void showPurchases(Product product) {
        cart.listProductsAndTotal(product);
    }

    void showBalance() {
        System.out.println(account);
    }
}

class Product {
    private String brand;
    private double price;

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

class Food extends Product {
}

class Clothes extends Product {
}

class Entertainment extends Product {
}

class Other extends Product {
}

class UI {
    private Scanner sc = new Scanner(System.in);

    int mainMenu() {
        String menu = """
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                0) Exit
                """;
        System.out.println(menu);
        System.out.println();
        return sc.nextInt();
    }

    int addPurchaseMenu() {
        String menu = """
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back
                """;
        System.out.println(menu);
        System.out.println();
        return sc.nextInt();
    }

    int showPurchaseMenu() {
        String menu = """
                Choose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back
                """;
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

    Product addPurchase(Product newProduct) {
        System.out.println("Enter purchase name:");
        String desc = sc.next();
        System.out.println("Enter its price:");
        double price = sc.nextDouble();
        System.out.println("Purchase was added!\n");
        newProduct.setBrand(desc);
        newProduct.setPrice(price);
        return newProduct;
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

    void listProductsAndTotal(Product product) {
        Predicate<Product> checkPurchadeType = p -> product.getClass().isInstance(p);

        if (getProducts().isPresent()) {
            List<Product> productList = getProducts().get().stream().filter(checkPurchadeType).toList();
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