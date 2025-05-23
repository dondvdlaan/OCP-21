package dev.manyroads.localdate.example1;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate start = LocalDate.of(
                scanner.nextInt(),
                scanner.nextInt(),
                1
        );

        start.datesUntil(start.plusMonths(1))
                .filter(it -> it.getDayOfWeek() == DayOfWeek.MONDAY)
                .forEach(System.out::println);
    }
}

