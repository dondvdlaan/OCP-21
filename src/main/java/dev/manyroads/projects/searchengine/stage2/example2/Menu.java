package dev.manyroads.projects.searchengine.stage2.example2;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public int getNumberOfLines() {
        System.out.println("Enter the number of people:");
        return Integer.parseInt(scanner.nextLine());
    }

    public String getInputLine() {
        return scanner.nextLine();
    }

    public int getNumberOfQueries() {
        System.out.println("Enter the number of search queries:");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayResults(List<String> results) {
        if (results.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            System.out.println("Found people:");
            for (String result : results) {
                System.out.println(result);
            }
        }
    }
}
