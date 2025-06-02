package dev.manyroads.projects.budgetmanager.stage1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Main {
    public static void main(String[] args) {
        new BudgetManager().countMyMoney();
    }
}

class BudgetManager {
    List<Product> products;
    UI ui;

    public BudgetManager() {
        ui = new UI();
    }

    void countMyMoney() {
        products = ui.listUserInputs();
        double tot = products.stream().map(Product::getPrice).reduce(Double::sum).orElse(0D);
        ui.listProductsAndTotal(products, tot);
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
        return String.format("%s$%.2f", brand, price);
    }
}

class UI {
    final Scanner sc = new Scanner(System.in);

    List<Product> listUserInputs() {
        List<Product> products = new ArrayList<>();
        try (sc) {
            while (sc.hasNext()) {
                String[] words = sc.nextLine().split("\\s+");
                String tPrice = words[words.length - 1];
                var sb = new StringBuilder();
                String tDesc = String.join(" ", words).trim();
                String desc = tDesc.substring(0, tDesc.length() - tPrice.length());
                double price = Double.parseDouble(tPrice.substring(1));
                products.add(new Product(desc, price));
            }
        }
        return products;
    }

    void listProductsAndTotal(List<Product> products, double tot) {
        products.forEach(System.out::println);
        System.out.println();
        System.out.printf("Total: $%.2f\n", tot);
    }
}
