package dev.manyroads.stringsandstuff.longestword;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * In the given string find the first longest word and output it.
 * <p>
 * Input data
 * Given a string in a single line. Words in the string are separated by a single space.
 * <p>
 * Output data
 * Output the longest word. If there are several such words, you should output the one, which occurs earlier.
 * <p>
 * Sample Input 1:
 * one two three four five six
 * Sample Output 1:
 * three
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] lineArray = scanner.nextLine().split(" ");
        int indexLongest = 0;
        for (int i = 0; i < lineArray.length; i++) {
            indexLongest = lineArray[i].length() > lineArray[indexLongest].length() ? i : indexLongest;
        }
        System.out.println(lineArray[indexLongest]);
        // sorts them in descending order of their lengths
        Arrays.stream(lineArray).sorted((a, b) -> b.length() - a.length()).findFirst().ifPresent(System.out::println);
        Arrays.stream(lineArray).sorted(Comparator.comparing(String::length)).findFirst().ifPresent(System.out::println);
        Arrays.stream(lineArray).max(Comparator.comparing(String::length)).ifPresent(System.out::println);
    }
}
