package dev.manyroads.projects.searchengine.stage5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository<T> {

    private final List<T> list;
    private final Map<String, List<Integer>> invertedIndex;

    public Repository() {
        this.list = new ArrayList<>();
        this.invertedIndex = new HashMap<>();
    }

    public List<T> getList() {
        return Collections.unmodifiableList(list);
    }

    public void add(T item) {
        this.list.add(item);
    }

    public T get(int index) {
        return this.list.get(index);
    }

    public Map<String, List<Integer>> getInvertedIndex() {
        return invertedIndex;
    }
}
