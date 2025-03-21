package dev.manyroads.exceptions;

import java.util.Scanner;

/**
 * Consider a method that takes a string and converts it to a double. If the input string happens to be null or of an unsuitable format,
 * a runtime exception occurs and the program fails.
 * Fix the method so it would catch any exception and return the default value 0 (zero) if an exception occurred.
 */
public class Converter {

    static double convertToDouble(String string) {
        try {
            return Double.parseDouble(string);
        } catch (NullPointerException | NumberFormatException ex) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(convertToDouble(scanner.next()));
    }
}
