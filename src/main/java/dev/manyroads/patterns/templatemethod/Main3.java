package dev.manyroads.patterns.templatemethod;

import java.util.Scanner;

/**
 * Career
 * There are three classes: an abstract class Career, and two concrete classes, Engineer and DataScientist.
 * Your task is to implement the abstract class Career with a template method called execute() and an abstract
 * method called work() using the following algorithm:
 * <p>
 * Dream
 * Plan
 * Study
 * Work
 * Make the classes Engineer and DataScientist inherit from the Career class and implement the methods according
 * to the console output.
 * <p>
 * Sample Input 1:
 * <p>
 * engineer
 * Sample Output 1:
 * <p>
 * Dream big!
 * Draw a plan!
 * Study!
 * Work as a Full Stack Engineer
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String profession = scanner.next().toLowerCase();
        if ("engineer".equals(profession)) {
            Career engineer = new Engineer("Full Stack Engineer");
            engineer.execute();
        } else if ("scientist".equals(profession)) {
            Career engineer = new Engineer("Data Scientist");
            engineer.execute();
        } else System.out.println("Error");
    }
}

abstract class Career {

    public void execute() {
        dream();
        plan();
        study();
        work();
    }

    void dream() {
        System.out.println("Dream big!");
    }

    void plan() {
        System.out.println("Draw a plan!");
    }

    void study() {
        System.out.println("Study!");
    }

    abstract void work();
}

class Engineer extends Career {
    String profession;

    public Engineer(String profession) {
        this.profession = profession;
    }

    @Override
    void work() {
        System.out.println("Work as a " + profession);
    }
}

class DataScientist extends Career {
    String profession;

    public DataScientist(String profession) {
        this.profession = profession;
    }

    @Override
    void work() {
        System.out.println("Work as a " + profession);
    }
}
