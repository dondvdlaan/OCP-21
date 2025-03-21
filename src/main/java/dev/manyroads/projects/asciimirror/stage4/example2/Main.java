package dev.manyroads.projects.asciimirror.stage4.example2;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the file path:");
        Path path = Path.of(sc.nextLine());

        List<String> animal = new ArrayList<>();

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(animal::add);
        } catch (UncheckedIOException | IOException e) {
            System.out.println("File not found!");
        }

        printDoubled(animal);
    }

    private static void printDoubled(List<String> animal) {
        int maxLength = animal.stream().
                max(Comparator.comparing(String::length))
                .orElse("").length();

        animal.stream()
                .map(s -> s + " ".repeat(maxLength - s.length()))
                .forEach(s -> System.out.println(s + " | " + s));
    }
}
