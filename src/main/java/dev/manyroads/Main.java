package dev.manyroads;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    enum Currency {
        USD("United States dollar"),
        EUR("Euro"),
        GBP("Pound sterling"),
        RUB("Russian ruble"),
        UAH("Ukrainian hryvnia"),
        KZT("Kazakhstani tenge"),
        CAD("Canadian dollar"),
        JPY("Japanese yen"),
        CNY( "Chinese yuan");

        final String description;

        Currency(String description) {
            this.description = description;
        }
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            String[] words = sc.nextLine().split("\\s+");
            int maxLength = words[0].length();
            int index = 0;
            for (int i = 0; i < words.length - 1; i++) {
                if (words[i + 1].length() > words[i].length()) {
                    maxLength = words[i + 1].length();
                    index = i + 1;
                }
            }
            System.out.println(words[index]);
            var l = Stream.of(words).max(Comparator.comparing(String::length)).orElse("");
            System.out.println(l);
        }


    }
}