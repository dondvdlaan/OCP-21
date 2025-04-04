package dev.manyroads.projects.searchengine.stage3;

import java.lang.String;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

import static dev.manyroads.projects.searchengine.stage3.SearchEngine.searchList;
import static java.lang.System.exit;

public class UI {
    Supplier<String> userImport = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");

    void menu(Repository<String> repo) {
        while (true) {
            System.out.println(Messages.MAIN_MENU.description);
            String choice = userImport.get();
            switch (choice) {
                case "1" -> findPeople(repo);
                case "2" -> printPeople(repo);
                case "0" -> exit(0);
                default -> System.out.println(Messages.WRONG_OPTION.description);
            }
        }
    }

    void intakePeople(Repository<String> repo) {
        System.out.println(Messages.NR_PEOPLE.description);
        int nrOfPeople = Integer.parseInt(userImport.get());

        System.out.println(Messages.ENTER_PEOPLE.description);
        for (int i = 1; i <= nrOfPeople; i++) {
            repo.add(userImport.get());
        }
    }

    void findPeople(Repository<String> repo) {
        //System.out.println(Messages.NR_QUERIES.description);
        //int nrOfQueries = Integer.parseInt(userImport.get());

        for (int i = 1; i <= 1; i++) {
            System.out.println(Messages.SEARCH_QUERY.description);
            List<String> res = searchList(repo, userImport.get());
            displayPeople(res);
        }
    }

    void displayPeople(List<String> list) {
        if (list.isEmpty()) {
            System.out.println(Messages.NO_MATCH_PEOPLE.description);
            return;
        }
        System.out.println(Messages.FOUND_PEOPLE.description);
        list.forEach(System.out::println);
    }

    void printPeople(Repository<String> repo) {
        System.out.println(Messages.LIST_PEOPLE.description);
       repo.getList().forEach(System.out::println);
    }
}
