package dev.manyroads.comparable.people;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * For the example given in the topic about people, implement the compareTo method.
 * It should compare people by name, and if they have the same name, compare them by age.
 */
public class Main {
    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();
        people.add(new Person("n1", 1, 2, 3));
        people.add(new Person("n2", 4, 5, 6));
        people.add(new Person("n2", -4, 5, 6));
        people.add(new Person("n3", 7, 8, 9));

        Collections.sort(people);
        people.forEach(System.out::println);
    }
}

class Person implements Comparable<Person> {
    @Getter
    private String name;
    @Getter
    private int age;
    private int height;
    private int weight;

    public Person(String name, int age, int height, int weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Person person) {
        int comparison = name.compareTo(person.getName());
        if (comparison == 0) return Integer.compare(age, person.getAge());
        return comparison;
    }

    // alter 1
//    @Override
//    public int compareTo(Person otherPerson) {
//        return Comparator.comparing(Person::getName)
//                .thenComparing(Person::getAge)
//                .compare(this, otherPerson);
//    }
    // alter 2
    //@Override
//    public int compareTo(Person otherPerson) {
//        // add your code here!
//        if (getName().compareTo(otherPerson.getName()) == 0) {
//            return getAge() - otherPerson.getAge();
//        }
//        return getName().compareTo(otherPerson.getName());
//    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    // getters, setters
}
