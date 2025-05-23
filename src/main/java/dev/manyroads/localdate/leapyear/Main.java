package dev.manyroads.localdate.leapyear;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String year = scanner.next();

        System.out.println(LocalDate.parse(year).isLeapYear());


    }

    // alternative
//    public class Main {
//        public static void main(String[] args) {
//            Scanner sc = new Scanner(System.in);
//            LocalDate date = LocalDate.parse(sc.nextLine());
//            System.out.println(LocalDate.of(date.getYear(),2,1).lengthOfMonth() == 29);
//        }
//    }

    // alternative 2
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        // Add `LocalDate` object creation here
//        String dateString = sc.next();
//        sc.close();
//        LocalDate date = LocalDate.parse(dateString);
//
//        // Check if the year from the date is a leap year
//        boolean isLeapYear = date.lengthOfYear() > 365;
//
//        // Print `true` or `false` based on the result
//        System.out.println(isLeapYear);
//    }
}
