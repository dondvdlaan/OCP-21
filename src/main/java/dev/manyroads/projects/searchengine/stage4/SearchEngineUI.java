package dev.manyroads.projects.searchengine.stage4;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

import static java.lang.System.exit;

public class SearchEngineUI implements UI {
    Supplier<String> userImport = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");

    public void menu(Repository<Person> repo) {
        while (true) {
            System.out.println(Messages.MAIN_MENU.description);
            String choice = userImport.get();
            switch (choice) {
                case "1" -> findPeople(repo);
                case "2" -> printPeople(repo);
                case "0" -> {
                    System.out.println(Messages.BYE_NOW.description);
                    exit(0);
                }
                default -> System.out.println(Messages.WRONG_OPTION.description);
            }
        }

    }

    void intakePeopleManual(Repository<Person> repo) {
        System.out.println(Messages.NR_PEOPLE.description);
        int nrOfPeople = Integer.parseInt(userImport.get());

        System.out.println(Messages.ENTER_PEOPLE.description);
        for (int i = 1; i <= nrOfPeople; i++) {
            String[] data = userImport.get().trim().split("\\s+");
            switch (data.length) {
                case 1 -> repo.add(new Person(data[0]));
                case 2 -> repo.add(new Person(data[0], data[1]));
                case 3 -> repo.add(new Person(data[0], data[1], data[2]));
            }
        }
    }

    public void intakePeopleFromFile(Repository<Person> repo, String pathToFile) {
        List<String> list = readFromFile(pathToFile);
        for (String line : list) {
            String[] data = line.trim().split("\\s+");
            switch (data.length) {
                case 1 -> repo.add(new Person(data[0]));
                case 2 -> repo.add(new Person(data[0], data[1]));
                case 3 -> repo.add(new Person(data[0], data[1], data[2]));
            }
        }
    }

    List<String> readFromFile(String pathToFile) {
        File file = new File(pathToFile);
        if (!(file.exists() && file.isFile())) {
            System.out.println("File not found!");
            return List.of();
        }
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                list.add(scanner.nextLine());
            }
        } catch (InputMismatchException ex) {
            System.out.println("FoutjeI: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("FoutjeII: " + ex.getMessage());
        }
        return list;
    }

    void findPeople(Repository<Person> repo) {
        //System.out.println(Messages.NR_QUERIES.description);
        //int nrOfQueries = Integer.parseInt(userImport.get());

        for (int i = 1; i <= 1; i++) {
            System.out.println(Messages.SEARCH_QUERY.description);
            List<Person> res = SearchEngine.searchList(repo, userImport.get());
            displayPeople(res);
        }
    }

    void displayPeople(List<Person> list) {
        if (list.isEmpty()) {
            System.out.println(Messages.NO_MATCH_PEOPLE.description);
            return;
        }
        System.out.println(Messages.FOUND_PEOPLE.description);
        list.forEach(System.out::println);
    }

    void printPeople(Repository<Person> repo) {
        System.out.println(Messages.LIST_PEOPLE.description);
        repo.getList().forEach(System.out::println);
    }
}
