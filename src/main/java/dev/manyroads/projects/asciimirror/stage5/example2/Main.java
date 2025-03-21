package dev.manyroads.projects.asciimirror.stage5.example2;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");

        try {
            List<String> linesList = Files.readAllLines(Paths.get(scanner.nextLine()));

            int maxLength = linesList.stream()
                    .mapToInt(String::length)
                    .max()
                    .getAsInt();

            Function<String, String> reverser = line -> line.chars()
                    .map(c -> switch (c) {
                        case '<' -> '>';
                        case '>' -> '<';
                        case '[' -> ']';
                        case ']' -> '[';
                        case '{' -> '}';
                        case '}' -> '{';
                        case '(' -> ')';
                        case ')' -> '(';
                        case '/' -> '\\';
                        case '\\' -> '/';
                        default -> c;
                    })
                    .mapToObj(c -> String.valueOf((char) c))
                    .reduce((c1, c2) -> c1 + c2)
                    .get();

            linesList.stream()
                    .map(line -> line + " ".repeat(maxLength - line.length()))
                    .map(line -> line + " | " + new StringBuilder(reverser.apply(line)).reverse())
                    .forEach(System.out::println);
        } catch (UncheckedIOException | IOException e) {
            System.out.println("File not found!");
        }
    }
}
