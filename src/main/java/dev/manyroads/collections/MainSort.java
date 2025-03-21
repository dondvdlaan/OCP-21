package dev.manyroads.collections;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Sorting the digits of an integer in ascending order
 * Implement a program in Java that takes an integer as input. Add a rule to have the program convert that integer
 * into a List with each digit as an element. Then, use the Collections framework to sort the list in
 * ascending order. Your program should print the sorted list as the output.
 * Input: 1234
 * Output: [1, 2, 3, 4]
 */
public class MainSort {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        List<Integer> lIts = convertToList2(number);
        Collections.sort(lIts);
        lIts.sort(Comparator.reverseOrder());
        System.out.println(lIts);
    }

    public static List<Integer> convertToList(int number) {
        String sInt = String.valueOf(number);
        List<Integer> lInts = new ArrayList<>();
        for (int i = 0; i < sInt.length(); i++) {
            lInts.add(Integer.parseInt(sInt.substring(i, i + 1)));
        }
        return lInts;
    }

    public static List<Integer> convertToList2(int number) {
        return new ArrayList<>( Arrays.stream(String.valueOf(number).split("")).map(Integer::valueOf).toList());
    }
}
