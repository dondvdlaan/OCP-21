package dev.manyroads.projects.asciimirror.stage4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String printCow() {
        return """
                            ^__^
                    _______/(oo)
                /\\/(       /(__)
                   | w----||   \s
                   ||     ||   \s
                """;
    }

    static void printFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }

    static void oddMirrors(File file) {
        try {
            List<String> list = Files.readAllLines(file.toPath());
            Integer longestLine = list.stream().map(String::length).reduce(Integer::max).orElse(0);
            String pad = "%-" + longestLine + "s";
            list.stream().map(s -> String.format(pad, s)).map(s -> s + " | " + s).forEach(System.out::println);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Objectives
     * Change your code so that when the program reads a new line from the file, it writes it to a list of
     * the String type first. After that, find out the longest string in the list and format others by
     * whitespaces. Let's call these new strings the modified ones. All you need to do is to output those
     * modified strings by the following pattern: {modified line} | {modified line}
     * Example 1
     * Input the file path:
     * > C:\ASCII_Animals\MooFolder\Cow.txt
     * ^__^ |             ^__^
     * _______/(oo) |     _______/(oo)
     * /\/(       /(__) | /\/(       /(__)
     * | w----||     |    | w----||
     * ||     ||     |    ||     ||
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Input the file path:");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        System.out.println(file.toPath());

        if (file.exists()) {
            oddMirrors(file);
        } else System.out.println("File not found!");
    }
}
