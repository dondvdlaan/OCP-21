package dev.manyroads.localdate;


import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        int daysOffset = scanner.nextInt();

        int year = LocalDate.parse(date).getYear();
        int i = 0;
        while (true) {
            LocalDate nextDate = LocalDate.parse(date).plusDays(daysOffset * i);
            if (nextDate.getYear()== year) System.out.println(nextDate);
            else System.exit(0);
            i++;
        }
        // Alternative

//        final String dateString = scanner.next();
//        final LocalDate date2 = LocalDate.parse(dateString);
//        final int offset = scanner.nextInt();
//
//        for (LocalDate j = date2; j.getYear() == date2.getYear(); j = j.plusDays(offset)) {
//            System.out.println(j.toString());
//        }

        // alternative 2
//        StringBuilder output = new StringBuilder();
//
//        LocalDate start = LocalDate.parse(scanner.nextLine());
//        LocalDate end = LocalDate.ofYearDay(start.getYear() + 1, 1);
//        int offset = scanner.nextInt();
//
//        start.datesUntil(end, Period.ofDays(offset))
//                .forEach(d -> output.append(d).append(System.getProperty("line.separator")));
//
//        System.out.print(output);
    }
}

