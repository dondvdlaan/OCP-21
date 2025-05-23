package dev.manyroads.localdate.example6;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int year = s.nextInt();
        int month = s.nextInt();
        LocalDate date = LocalDate.of(year, month, 1);
        DayOfWeek day = date.getDayOfWeek();
        if (!DayOfWeek.MONDAY.equals(day)) {
            date = date.plusDays(8 - day.getValue());
        }
        while (date.getMonth().getValue() == month) {
            System.out.println(date);
            date = date.plusWeeks(1);
        }
    }
}
