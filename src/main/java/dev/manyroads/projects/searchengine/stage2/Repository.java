package dev.manyroads.projects.searchengine.stage2;

import java.util.List;

public class Repository<T> {

    List<T> people;

    public Repository(List<T> people) {
        this.people = people;
    }

    public List<T> getPeople() {
        return people;
    }

    public void setPeople(List<T> people) {
        this.people = people;
    }

    public void add(T item) {
        this.people.add(item);
    }

    public T get(int index) {
        return this.people.get(index);
    }
}
