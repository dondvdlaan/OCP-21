package dev.manyroads.patterns.strategypattern;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Max and Min
 * Use the strategy pattern to implement algorithms to find max and min values in a given array. The basic structure of
 * the provided classes is described below: your job is to complete it.
 *
 * The class Finder represents the general finding algorithm itself. It has find(int[] numbers) method that returns the
 * result of finding according to the specified strategy.
 *
 * The interface FindingStrategy provides getResult() method to write new concrete finding strategies.
 *
 * If the array is empty, the Finder should return Integer.MAX_VALUE in case of finding the min value and Integer.MIN_VALUE
 * in case of finding the max value.
 *
 * Tests 1-5 check MinFindingStrategy, tests 6-10 check MaxFindingStrategy. Do not forget to check your solution when the
 * passed array has the size 0 or 1.
 */
class Finder {

    private FindingStrategy strategy;

    public Finder(FindingStrategy strategy) {
        this.strategy=strategy;
    }

    /**
     * It performs the search algorithm according to the given strategy
     */
    public int find(int[] numbers) {
        return strategy.getResult(numbers);
    }
}

interface FindingStrategy {

    /**
     * Returns search result
     */
    int getResult(int[] numbers);

}

class MaxFindingStrategy implements FindingStrategy {

    public int getResult(int[] numbers) {
        return Arrays.stream(numbers).max().orElse(Integer.MIN_VALUE);
    }
}

class MinFindingStrategy implements FindingStrategy {

    public int getResult(int[] numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        final String[] elements = scanner.nextLine().split("\\s+");
        int[] numbers = null;

        if ("EMPTY".equals(elements[0])) {
            numbers = new int[0];
        } else {
            numbers = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                numbers[i] = Integer.parseInt(elements[i]);
            }
        }

        final String type = scanner.nextLine();

        Finder finder = null;

        switch (type) {
            case "MIN":
                finder = new Finder(new MinFindingStrategy());
                break;
            case "MAX":
                finder = new Finder(new MaxFindingStrategy());
                break;
            default:
                break;
        }

        if (finder == null) {
            throw new RuntimeException(
                    "Unknown strategy type passed. Please, write to the author of the problem.");
        }

        System.out.println(finder.find(numbers));
    }
}