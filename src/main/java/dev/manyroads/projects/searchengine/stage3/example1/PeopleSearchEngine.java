package dev.manyroads.projects.searchengine.stage3.example1;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ag on 04-Jun-20 9:25 PM
 */
public class PeopleSearchEngine {

    private final PersonDB personDB;

    public PeopleSearchEngine(PersonDB personDB) {
        this.personDB = personDB;
    }

    public void search(String query) {
        List<Person> foundData = new ArrayList<>();

        for (Person p : this.personDB.getPersonList()) {
            if (p.toString().toLowerCase().contains(query)) {
                foundData.add(p);
            }
        }

        printFoundData(foundData);
    }

    private void printFoundData(Collection<Person> people) {
        if (people.isEmpty()) {
            System.out.println("\nNo matching people found.");
            return;
        }

        people.forEach(System.out::println);
    }

}
