package dev.manyroads.lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * You're given a sequence of strings separated by spaces. Read the sequence from the standard input and store
 * all strings in a list. Output the list to the standard output using System.out.println(yourList).
 * <p>
 * The order of elements must be the same as in the input.
 * <p>
 * Sample Input 1:
 * Google Oracle JetBrains
 * Sample Output 1:
 * [Google, Oracle, JetBrains]
 */
public class MainList3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String re = "\\s+";
        List<String> list = Arrays.stream(scanner.nextLine().split(re)).toList();   // sol 1
        System.out.println(list);
        List<String> list2 = List.of(scanner.nextLine().split(re));                 // sol 2
        System.out.println(list2);
        System.out.println(Arrays.toString(scanner.nextLine().split(re)));          // sol 3
        Collection<String> col = Arrays.stream(scanner.nextLine().split(re)).toList();
        System.out.println(col);                                                    // sol 4
    }
}
