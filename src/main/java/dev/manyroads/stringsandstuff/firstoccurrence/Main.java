package dev.manyroads.stringsandstuff.firstoccurrence;

import java.util.Scanner;

/**
 * Write a program that takes a sentence as input and returns the index of the first occurrence of the word "the"
 * (can be part of a word), regardless of the capitalization. If there is no occurrence of "the",
 * the program should output -1.
 *
 * For instance, if the sentence is “The apple is red.” the output should be 0, if the sentence is
 * “I ate the red apple.” the output should be 6, and if the sentence is “I love apples.” the output should be -1.
 *
 * Note, the and The are equal.
 *
 * You can use .contains("the") method to check if the sentence contains "the".
 * Then you can check the index for its first occurrence using .indexOf("the") method.
 * To avoid conflict between "the" or "The", convert the sentence into uppercase or lowercase before checking.
 * Sample Input 1:
 * The apple is red.
 * Sample Output 1:
 *
 * 0
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String keyWord = "the";
        String line = scanner.nextLine();
        if(line.toLowerCase().contains(keyWord)) System.out.println(line.toLowerCase().indexOf(keyWord));
        else System.out.println(-1);

        System.out.println(new Scanner(System.in).nextLine().toLowerCase().indexOf("the"));
    }

}
