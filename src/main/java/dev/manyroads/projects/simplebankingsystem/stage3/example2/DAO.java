package dev.manyroads.projects.simplebankingsystem.stage3.example2;


public interface DAO<T> {
    void add(T element);

    T findByNumber(String number);

}
