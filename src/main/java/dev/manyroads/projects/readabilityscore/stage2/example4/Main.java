package dev.manyroads.projects.readabilityscore.stage2.example4;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] sentences = scanner.nextLine().trim().split("[!.?]\\s?");

        Arrays.stream(sentences)
                .mapToInt(sentence -> sentence.split("\\s+").length)
                .average()
                .ifPresent(avg -> System.out.println(avg > 10.0 ? "HARD" : "EASY"));
    }
}
