package dev.manyroads.projects.readabilityscore.stage2.example3;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(isHardToRead(new Scanner(System.in).nextLine()) ? "HARD" : "EASY");
    }

    private static boolean isHardToRead(String text) {
        double averageWords = Arrays.stream(text.split("[.?!]\\s+"))
                .mapToInt(s -> s.split("\\s+").length)
                .average()
                .orElseThrow();
        return averageWords > 10;
    }
}
