package dev.manyroads.projects.searchengine.stage6.example;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserInterface {

    public Scanner scanner = new Scanner(System.in);
    private final String[] source;
    private final Repository repository = new Repository();

    public UserInterface(String[] source) {
        this.source = source;
    }

    public void start() {
        repository.initRepository(source);
        int option;
        do {
            option = choiceMainMenu();
            switch (option) {
                case 1:
                    choiceStrategy();
                    break;
                case 2:
                    repository.printAllData();
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
            }
        } while (option != 0);
    }

    public int choiceMainMenu() {
        int option;
        do {
            System.out.println("=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit");
            option = Integer.parseInt(scanner.nextLine());
            if (option < 0 || option > 2) {
                System.out.println("Incorrect option! Try again.\n");
            }
        } while (option < 0 || option > 2);
        return option;
    }

    public void choiceStrategy() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategy = scanner.nextLine();
        System.out.println("Enter a name or email to search all suitable people.");
        String searchData = scanner.nextLine();
        Set<Integer> resultIndex;
        switch (strategy) {
            case "ALL" :
                SearchEngine allSearcher = new AllSearcher(repository);
                resultIndex = allSearcher.searchingData(searchData);
                printResult(repository.getStorage(), resultIndex);
                break;
            case "ANY" :
                SearchEngine anySearcher = new AnySearcher(repository);
                resultIndex = anySearcher.searchingData(searchData);
                printResult(repository.getStorage(), resultIndex);
                break;
            case "NONE" :
                SearchEngine noneSearcher = new NoneSearcher(repository);
                resultIndex = noneSearcher.searchingData(searchData);
                printResult(repository.getStorage(), resultIndex);
                break;
        }
    }

    public void printResult(List<DataModel> storage, Set<Integer> resultIndex) {
        if (resultIndex.size() > 0) {
            System.out.printf("%d persons found:\n", resultIndex.size());
            for (Integer each : resultIndex) {
                System.out.println(storage.get(each).getDataLine());
            }
        } else {
            System.out.println("No matching people found.");
        }
        System.out.println();
    }

}
