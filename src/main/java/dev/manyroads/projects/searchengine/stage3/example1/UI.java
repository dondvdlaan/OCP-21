package dev.manyroads.projects.searchengine.stage3.example1;

import java.util.Scanner;

/**
 * Created by ag on 04-Jun-20 8:37 PM
 */
public class UI {

    private Scanner scanner;
    private PeopleSearchEngine searchEngine;
    private PersonDB personDB;

    public UI() {
        this.scanner = new Scanner(System.in);
        this.personDB = new PersonDB();
        this.searchEngine = new PeopleSearchEngine(this.personDB);
    }

    public void start() {
        int n = getNumberOfPeople();
        getData(n);
        menu();
    }

    private void menu() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            String choice = this.scanner.nextLine();
            switch (choice) {
                case "1":
                    search();
                    break;
                case "2":
                    personDB.printAllData();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("\nIncorrect option! Try again.");
                    break;
            }
        }

        System.out.println("\nBye!");
    }

    private void showMenu() {
        System.out.println(
                "\n=== Menu ===\n" +
                        "1. Find a person\n" +
                        "2. Print all people\n" +
                        "0. Exit"
        );
    }

    private int getNumberOfPeople() {
        System.out.println("Enter the number of people:"); // FIXME: negative number of people
        return Integer.parseInt(scanner.nextLine());
    }

    private void getData(int n) {
        System.out.println("Enter all people:");

        while (n-- > 0) {
            String[] data = scanner.nextLine().trim().split("\\s+");

            switch (data.length) {
                case 3: // user provided: firstName, lastName and email
                    personDB.insertData(new Person(data[0], data[1], data[2]));
                    break;
                case 2: // user provided: firstName, lastName
                    personDB.insertData(new Person(data[0], data[1]));
                    break;
                case 1: // user provided single word assume it is firstName
                    personDB.insertData(new Person(data[0]));
                    break;
                default: // data contains more then 3 words or less then 1 is not permitted
                    System.out.println("Data can have only 3 words, first name, last name and email.");
                    break;
            }
        }
    }

    private void search() {
        System.out.println("\nEnter a name or email to search all suitable people.");
        String query = scanner.nextLine().toLowerCase();

        searchEngine.search(query);
    }

}