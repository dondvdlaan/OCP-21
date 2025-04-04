package dev.manyroads.projects.searchengine.stage4;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {

    private final List<T> list;

    public Repository() {
        this.list = new ArrayList<>();
    }

    public List<T> getList() {
        return list;
    }

    public void add(T item) {
        this.list.add(item);
    }

    public T get(int index) {
        return this.list.get(index);
    }
}
