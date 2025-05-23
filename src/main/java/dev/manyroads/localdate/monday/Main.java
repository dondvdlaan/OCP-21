package dev.manyroads.localdate.monday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int month = scanner.nextInt();

        LocalDate startDate = LocalDate.of(year, month, 1);
        for (int i = 1; i <= startDate.lengthOfMonth(); i++) {
            LocalDate currentDate = startDate.withDayOfMonth(i);
            //LocalDate currentDate = startDate.plusDays(i);
            if (currentDate.getDayOfWeek() == DayOfWeek.MONDAY
            )
                //if (currentDate.getDayOfWeek() == DayOfWeek.MONDAY && currentDate.getMonth() == startDate.getMonth())
                System.out.println(currentDate);
        }


    }
}
