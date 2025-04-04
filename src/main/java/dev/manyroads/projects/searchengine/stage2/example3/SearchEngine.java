package dev.manyroads.projects.searchengine.stage2.example3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SearchEngine {
    private final List<String> peopleList;

    public SearchEngine() {
        this.peopleList = new ArrayList<>();
    }

    public List<String> getPeopleList() {
        return peopleList;
    }

    public void addPerson(String person) {
        peopleList.add(person);
    }

    public void findPerson(String person) {
        boolean headerPrinted = false;
        for (String entry : peopleList) {
            if (entry.toLowerCase(Locale.US).contains(person)) {
                if (!headerPrinted) {
                    System.out.println("\nFound people:");
                    headerPrinted = true;
                }
                System.out.println(entry);
            }
        }
        if (!headerPrinted) {
            System.out.println("No matching people found.");
        }
    }
}
