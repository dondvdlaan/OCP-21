package dev.manyroads.ifs;

import java.time.Year;
import java.util.Scanner;

/**
 * Find whether a given year is a leap year.
 * <p>
 * Just a reminder: a leap year is divisible by 4 AND NOT divisible by 100 OR divisible by 400
 * (for example, the year 2000 is a leap year, but the year 2100 will not be a leap year).
 * <p>
 * The program should work correctly for the years 1900 ≤ n ≤ 3000.
 * Output "Leap" (case-sensitive) if the given year is a leap year, and "Regular" otherwise.
 * Sample Input 1:
 * 2100
 * Sample Output 1:
 * Regular
 * Sample Input 2:
 * 2000
 * Sample Output 2:
 * Leap
 */
public class MainIf2 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int y = scanner.nextInt();
            boolean leapYear = ((y % 4 == 0) && !(y % 100 == 0)) || ((y % 100 == 0) && (y % 400 == 0));
            if (leapYear) {
                System.out.println("Leap");
            } else {
                System.out.println("Regular");
            }
            // alternative
            System.out.println(Year.isLeap(y) ? "Leap" : "Regular");
        }
    }
}
