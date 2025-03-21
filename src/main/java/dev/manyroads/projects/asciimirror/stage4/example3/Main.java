package dev.manyroads.projects.asciimirror.stage4.example3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Input the file path:");
        String path = sc.nextLine();

        try {
            Path filePath = Paths.get(path);

            List<String> lines = Files.readAllLines(filePath);

            // find the longest String
            int maxLength = lines.stream()
                    .mapToInt(String::length)
                    .max().orElse(0);

            // Extend all strings to the maximum length
            List<String> paddedStrings = lines.stream()
                    .map(s -> String.format("%-" + maxLength + "s", s))  // Fill with spaces
                    .toList();

            // Display strings next to each other
            for (String line : paddedStrings) {
                System.out.println(line + " | " + line);
            }

        } catch (InvalidPathException | IOException e) {
            System.out.println("File not found");
        }
    }
}
