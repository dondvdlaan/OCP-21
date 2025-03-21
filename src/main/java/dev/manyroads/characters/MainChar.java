package dev.manyroads.characters;

import java.util.Scanner;

/**
 * Write a program in Java that receives a character from the input and returns the next character in the Unicode table.
 * Your program should read a single character from the input and print a single character as output.
 */
public class MainChar {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            char c = scanner.next().charAt(0);
            System.out.println(++c);
        }
    }
}
