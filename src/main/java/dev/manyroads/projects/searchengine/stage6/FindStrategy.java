package dev.manyroads.projects.searchengine.stage6;

import java.util.ArrayList;
import java.util.List;

/**
 * Template method Pattern
 */
public abstract class FindStrategy {
    String[] query;
    List<Person> list= new ArrayList<>();

    public void findPeople() {
        enterFindCriteria();
        find();
        displayPeople();
    }

    void enterFindCriteria() {
        System.out.println(Messages.SEARCH_QUERY.description);
        query = Util.userImport.get().split("\\s+");
    }

    abstract void find();

    void displayPeople() {
        if (list.isEmpty()) {
            System.out.println(Messages.NO_MATCH_PEOPLE.description);
            return;
        }
        System.out.println(Messages.FOUND_PEOPLE.description);
        list.forEach(System.out::println);
    }
}
