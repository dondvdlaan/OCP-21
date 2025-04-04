package dev.manyroads.projects.searchengine.stage2.example1;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    private final List<T> list = new ArrayList<>();

    public boolean add(T item) {
        if (item == null) {
            return false;
        }
        return list.add(item);
    }

    public boolean addAll(List<T> items) {
        return list.addAll(items);
    }

    public boolean remove(T item) {
        return list.remove(item);
    }

    public T remove(int index) {
        return list.remove(index);
    }

    public List<T> getAll() {
        return list;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T t : list) {
            sb.append(t).append("\n");
        }
        return sb.toString();
    }
}
