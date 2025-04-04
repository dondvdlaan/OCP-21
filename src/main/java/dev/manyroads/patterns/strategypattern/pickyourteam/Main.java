package dev.manyroads.patterns.strategypattern.pickyourteam;

import java.util.Scanner;

/**
 * Strategy Pattern
 * Imagine that you're creating teams to organize events.
 * You need a module for your program that will select the people for these teams.
 * <p>
 * There are only two selection algorithms:
 * take every k-th person, or every person if k =1;
 * take the last k people, or the last person if k = 1.
 * You decided to use the strategy pattern in the module because new selection algorithms will be added in the future.
 * Also, the pattern allows you to change the current algorithm at runtime.
 * <p>
 * The basic structure of classes is provided below, but it doesn't work properly yet.
 * Your goal is to implement the following methods:
 * <p>
 * setAlgorithm and selectPersons methods of the class SelectionContext;
 * select of the class TakePersonsWithStepAlgorithm to take every k-th person starting with the index 0 in the
 * same order as in the input array (when k is 3, then it must take 0, 3, 6, ... persons);
 * select of the class TakeLastPersonsAlgorithm to take the last k persons in the same order as the input array.
 * Perhaps, you should add some fields to the classes as well.
 * HINT: tests 1–4 check TakePersonsWithStepAlgorithm, tests 5–8 check TakeLastPersonsAlgorithm.
 * Do not forget to check your solution when the step is 1 or the input array consists of a single element.
 */
class SelectionContext {


    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        return algorithm.select(persons);
    }
}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {
    private int step;

    public TakePersonsWithStepAlgorithm(int step) {
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        int len = Math.ceilDiv(persons.length, step);
        if (step == 1 || persons.length == 1) return persons;
        Person[] selectedPersons = new Person[len];
        for (int i = 0, j = 0; i < persons.length; i += step) {
            selectedPersons[j] = persons[i];
            j++;
        }
        return selectedPersons;
    }
    // Alernative
    //    public Person[] select(Person[] persons) {
//        return IntStream.range(0, persons.length)
//                .filter(x -> x % step == 0)
//                .mapToObj(x -> persons[x])
//                .toArray(Person[]::new);
}

class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {
    private int count;

    public TakeLastPersonsAlgorithm(int count) {
        this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        Person[] selectedPersons = new Person[count];
        for (int i = persons.length - 1, j = 0; i > persons.length - count - 1; i--) {
            selectedPersons[count - j - 1] = persons[i];
            j++;
        }
        return selectedPersons;
    }
    // Alternative
//    public Person[] select(Person[] persons) {
//        return Arrays.stream(persons)
//                .skip(persons.length - count)
//                .toArray(Person[]::new);
//    }
    // Alternative2
//    public Person[] select(Person[] persons) {
//        Person[] p = new Person[count];
//        for (int i = 0, t = persons.length - count; i < count; i++) {
//            p[i] = persons[t + i];
//        }
//        return p;
//    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");

        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }
}
