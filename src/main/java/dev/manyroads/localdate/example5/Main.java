package dev.manyroads.localdate.example5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        var ints = Arrays.stream(new Scanner(System.in)
                        .nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        var date = LocalDate.of(ints[0], ints[1], 1);
        List<LocalDate> days = date.datesUntil(date.plusMonths(1))
                .filter(day ->
                        day.getDayOfWeek().equals(DayOfWeek.MONDAY))
                .collect(Collectors.toList());
        days.forEach(System.out::println);

    }
}