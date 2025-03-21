package dev.manyroads.ifs;

import java.util.Scanner;

/**
 * Given an integer as an input, print True if its value falls within the interval (−15,12]∪(14,17)∪[19,+∞).
 * Otherwise, print False (case sensitive).
 * <p>
 * Notes:
 * numbers denoted with a parenthesis () are exclusive;
 * numbers denoted with a square bracket [] are inclusive.
 */
public class MainIf3 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            boolean isInterval = (n > -15 && n <= 12) || (n > 14 && n < 17) || (n >= 19);
            if (isInterval) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }
}
