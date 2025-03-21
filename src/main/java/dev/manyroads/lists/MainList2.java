package dev.manyroads.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Implement a method that takes an integer value and two lists of numbers.
 * It must check if the value occurs the same number of times in both sequences.
 * <p>
 * Keep note however that you do not need to input any data yourself.
 * The values are provided via arguments to the method checkTheSameNumberOfTimes for this task.
 * <p>
 * In the following example, the first line contains the value, the second line is the first list,
 * the third line is another list.
 * <p>
 * All numbers are separated by whitespaces.
 * Input:
 * 3
 * 8 8 3 3 2
 * 1 3 3 2
 * Output:
 * true
 */
public class MainList2 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nr = Integer.parseInt(scanner.nextLine());
        List<Integer> lst1 = new ArrayList<>(Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .toList());
        List<Integer> lst2 = new ArrayList<>(Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .toList());
        System.out.println(checkTheSameNumberOfTimes(nr, lst1, lst2));
        System.out.println(checkTheSameNumberOfTimes2(nr, lst1, lst2));
        System.out.println(checkTheSameNumberOfTimes3(nr, lst1, lst2));

    }

    private static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        long timerNr = list1.stream().filter(n -> n == elem).count();
        long timerNr2 = list2.stream().filter(n -> n == elem).count();

        return timerNr == timerNr2;
    }

    private static boolean checkTheSameNumberOfTimes2(int elem, List<Integer> list1, List<Integer> list2) {
        return Collections.frequency(list1, elem) == Collections.frequency(list2, elem);
    }

    private static boolean checkTheSameNumberOfTimes3(int elem, List<Integer> list1, List<Integer> list2) {
        list1.removeIf(n -> n != elem);
        list2.removeIf(n -> n != elem);
        return list1.equals(list2);
    }
}
