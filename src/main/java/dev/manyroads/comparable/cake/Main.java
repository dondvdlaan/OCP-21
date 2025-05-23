package dev.manyroads.comparable.cake;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cake> cakes = new ArrayList<>();
        cakes.add(new Cake("c1","lekker",100));
        cakes.add(new Cake("cc1","lakker",101));
        cakes.add(new Cake("cbd1","lokker",102));

        Collections.sort(cakes);
        cakes.forEach(System.out::println);
    }
}

/**
 * define the ordering by name
 */
class Cake implements Comparable<Cake> {
    @Getter
    private String name;
    private String description;
    private int weight;

    @Override
    public int compareTo(Cake cake) {
        return Comparator.comparing(Cake::getName).compare(this, cake);
        //return name.compareTo(cake.getName());
    }

    public Cake(String name, String description, int weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                '}';
    }

    // getters and setters
}
