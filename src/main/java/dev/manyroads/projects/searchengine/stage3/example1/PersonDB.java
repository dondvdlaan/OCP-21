package dev.manyroads.projects.searchengine.stage3.example1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ag on 04-Jun-20 9:36 PM
 */
public class PersonDB {

    private final List<Person> personList;

    public PersonDB() {
        this.personList = new ArrayList<>();
    }

    public void insertData(Person person) {
        personList.add(person);
    }

    public List<Person> getPersonList() {
        // Don't allow client to make modification on personList data
        return Collections.unmodifiableList(this.personList);
    }

    public void printAllData() {
        System.out.println("\n=== List of people ===");
        personList.forEach(System.out::println);
    }
}