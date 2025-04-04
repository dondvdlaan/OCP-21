package dev.manyroads.projects.searchengine.stage2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Objectives
 * Write a program that reads text lines from the standard input and processes single-word queries.
 * The program must output all lines that contain the string from the query. For this stage, this should
 * include the case where the query string appears as a substring of one of the text lines. For example,
 * the query "bc" should be found in a line containing "abcd".
 * <p>
 * You may choose what the text represents in your project. For example, each line may describe:
 * a person represented by the first name, the last name, and optionally an email;
 * an address of a building represented by the country, city, state, street, and zip code;
 * a book represented by its ISBN, title, author/authors, publisher, and so on.
 * <p>
 * You can use any of these options or come up with your own, because your search algorithm should work
 * regardless of what the text actually represents.
 * <p>
 * Here is an example of a line. It contains three items: first name, last name, and this person's email.
 * Elsa Sanders elsa@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        new SearchEngine(new Repository<>(people)).run();
    }
}

class SearchEngine implements Runnable {

    Supplier<String> userImport = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");
    List<String> people = new ArrayList<>();
    Repository<Person> repo;

    public SearchEngine(Repository<Person> repo) {
        this.repo = repo;
    }

    void searchPeople(String keyWord) {
        int count = 0;
        List<Integer> index = new ArrayList<>();
        for (String line : people) {
            if (line.toLowerCase().contains(keyWord.toLowerCase())) index.add(count);
            count++;
        }
        if (index.isEmpty()) System.out.println(Messages.NO_MATCH_PEOPLE.description);
        else {
            System.out.println(Messages.FOUND_PEOPLE.description);
            for (int i = 0; i < index.size(); i++) {
                System.out.println(people.get(index.get(i)));
            }
        }
    }

    void searchPeople2(String keyWord) {
        String word = keyWord.toLowerCase();
        List<Person> publish = new ArrayList<>();
        for (Person p : repo.people) {
            if (p.getFirstName().toLowerCase().contains(word)) publish.add(p);
        }
        int count = 0;
        List<Integer> index = new ArrayList<>();
        for (String line : people) {
            if (line.toLowerCase().contains(keyWord.toLowerCase())) index.add(count);
            count++;
        }
        if (index.isEmpty()) System.out.println(Messages.NO_MATCH_PEOPLE.description);
        else {
            System.out.println(Messages.FOUND_PEOPLE.description);
            for (int i = 0; i < index.size(); i++) {
                System.out.println(people.get(index.get(i)));
            }
        }
    }

    void getPeople() {
        System.out.println(Messages.NR_PEOPLE.description);
        int nrOfPeople = Integer.parseInt(userImport.get());

        System.out.println(Messages.ENTER_PEOPLE.description);
        for (int i = 1; i <= nrOfPeople; i++) {
            people.add(userImport.get());
        }
    }

    void findPeople() {
        System.out.println(Messages.NR_QUERIES.description);
        int nrOfQueries = Integer.parseInt(userImport.get());

        for (int i = 1; i <= nrOfQueries; i++) {
            System.out.println(Messages.SEARCH_QUERY.description);
            searchPeople(userImport.get());
        }
    }

    @Override
    public void run() {
        getPeople();
        findPeople();
    }
}

enum Messages {
    NR_PEOPLE("Enter the number of people:"),
    ENTER_PEOPLE("Enter all people:"),
    NR_QUERIES("Enter the number of search queries:"),
    SEARCH_QUERY("Enter data to search people:"),
    FOUND_PEOPLE("Found people:"),
    NO_MATCH_PEOPLE("No matching people found.");

    String description;

    Messages(String description) {
        this.description = description;
    }
}
