package dev.manyroads.projects.searchengine.stage2.example3;

import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    private final SearchEngine searchEngine;
    private final Scanner scanner;

    public UserInterface() {
        this.searchEngine = new SearchEngine();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        readPeopleEntries(readHowManyEntries());
        findPerson(readHowManyQueries());
    }

    private int readHowManyEntries() {
        System.out.println("Enter the number of people:");
        System.out.print("> ");
        return Integer.parseInt(scanner.nextLine());
    }

    private void readPeopleEntries(int entries) {
        System.out.println("Enter all people:");
        for (int i = 0; i < entries; i++) {
            System.out.print("> ");
            searchEngine.addPerson(scanner.nextLine());
        }
    }

    private int readHowManyQueries() {
        System.out.println("\nEnter the number of search queries:");
        System.out.print("> ");
        return Integer.parseInt(scanner.nextLine());
    }

    private void findPerson(int queries) {
        for (int i = 0; i < queries; i++) {
            System.out.println("\nEnter data to search people:");
            System.out.print("> ");
            searchEngine.findPerson(scanner.nextLine().toLowerCase(Locale.US));
        }
    }
}
