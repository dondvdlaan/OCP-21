package dev.manyroads.localdate.example2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

class Main {
    private static final int DAYS_PER_WEEK = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();

        LocalDate date = LocalDate.of(year, month, 1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        while (date.getMonthValue() == month) {
            System.out.println(date);
            date = date.plusDays(DAYS_PER_WEEK);
        }
    }
}
