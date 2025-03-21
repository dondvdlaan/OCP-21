package dev.manyroads.projects.asciimirror.example2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String input = scanner.nextLine();

        File file = new File(input);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File not found!");
        } else {
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                for (String line : lines) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("File not found!");
            }
        }
    }
}

