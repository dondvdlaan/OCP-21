package dev.manyroads.characters;

import java.util.Scanner;

/**
 * Write a program that reads two lines and compares them without whitespaces.
 * The program should print true if both lines are equal, otherwise – false.
 * Sample Input 1:
 *   string
 * str ing
 * Sample Output 1:
 * true
 */
public class MainChar2 {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            String re = "\\s+";
            String l1 = scanner.nextLine().replaceAll(re, "");
            String l2 = scanner.nextLine().replaceAll(re, "");
            System.out.println(l1.equals(l2));
            String str = "Jav";
            str = (str + str.charAt(1)).toLowerCase();
            System.out.println(str);
        }
    }
}
