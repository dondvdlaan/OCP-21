package dev.manyroads.datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dateString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate inputDate = LocalDate.parse(dateString, formatter);
            LocalDate date10DaysBefore = inputDate.minusDays(10);
            System.out.println(date10DaysBefore.format(formatter));
        } catch (DateTimeParseException e) {
            System.err.println("Error: Invalid date format. Please use YYYY-MM-DD. " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
class ex2{
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            final int[] ints = IntStream.generate(sc::nextInt).limit(6).toArray();
            LocalTime time1 = LocalTime.of(ints[0],ints[1],ints[2]);
            LocalTime time2 = LocalTime.of(ints[3],ints[4],ints[5]);
            System.out.println(Duration.between(time1,time2).toSeconds());
        }
    }
}

