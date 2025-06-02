package dev.manyroads.projects.budgetmanager.stage2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * To make your application flexible and functional, add a menu that consists of 4 items.
 * * Add income — We must track both our expenses and our income. When this item is selected,
 * the program should ask to enter the amount of income.
 * * Add purchase — This item should add a purchase to the list. You also need to subtract the price of
 * the purchase from the income.
 * * Show the list of purchases — This menu item should display a list of all expenses in the order they were made.
 * * Balance — Show the balance which is equal to total income subtracted by total expenses during purchase.
 * * Exit — Print Bye! and exit the program. Make this item under number 0, not number 5.
 * <p>
 * Notice, that the amount of remaining money cannot be negative. In such cases, make the balance equal to $0.
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
            switch (service.menu()) {
                case 1 -> service.addIncomeToBalance();
                case 2 -> service.addPurchaseToCart();
                case 3 -> service.showPurchases();
                case 4 -> service.showBalance();
                case 0 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
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

    void addIncomeToBalance() {
        double income = ui.addIncome();
        account.addToBalance(income);
    }

    void addPurchaseToCart() {
        Product newProduct = ui.addPurchase();
        cart.addProductToCart(newProduct);
        account.deductBalance(newProduct.getPrice());
    }

    void showPurchases() {
        cart.listProductsAndTotal();
    }

    void showBalance() {
        System.out.println(account);
    }
}

class Product {
    private String brand;
    private double price;

    public Product(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", brand, price);
    }
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

    double getCartTot() {
        return products.stream().map(Product::getPrice).reduce(Double::sum).orElse(0D);
    }

    void addProductToCart(Product newProduct) {
        this.products.add(newProduct);
    }

    void listProductsAndTotal() {
        if (getProducts().isPresent() && getProducts().get().isEmpty()) {
            System.out.println("The purchase list is empty\n");
            return;
        } else {
            getProducts().get().forEach(System.out::println);
            System.out.println();
            System.out.printf("Total: $%.2f\n\n", getCartTot());
        }
    }
}
