package dev.manyroads.sets;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Output each of its elements with the loop with a new line.
 * output:
 * Mr.Green
 * Mr.Red
 * Mr.Yellow
 */
public class MainSet {
    public static void main(String[] args) {
        Set<String> nameSet = new TreeSet<>(Arrays.asList("Mr.Green", "Mr.Yellow", "Mr.Red"));
        nameSet.forEach(System.out::println);
    }
}
