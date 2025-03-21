package dev.manyroads.projects.asciimirror.stage5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    static void straightMirrors(Path pathToFile) throws IOException {
        List<String> list = Files.readAllLines(pathToFile);
        Integer longestLine = list.stream().map(String::length).reduce(Integer::max).orElse(0);
        String pad = "%-" + longestLine + "s";
        list.stream()
                .map(s -> String.format(pad, s))
                .map(s -> s + " | " + replaceNotHorizontallySymmetricalAndReverse(s))
                .forEach(System.out::println);
    }

//    static void straightMirrors(File file) {
//        try {
//            List<String> list = Files.readAllLines(file.toPath());
//            Integer longestLine = list.stream().map(String::length).reduce(Integer::max).orElse(0);
//            String pad = "%-" + longestLine + "s";
//            List<String> lModified = list.stream().map(s -> String.format(pad, s)).toList();
//            List<String> lReversed = lModified.stream().map(Main::replaceNotHorizontallySymmetrical).toList();
//            for (int i = 0; i < lModified.size(); i++) {
//                System.out.println(lModified.get(i) + " | " + lReversed.get(i));
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    static String replaceNotHorizontallySymmetricalAndReverse(String s) {
        StringBuilder replacedS = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '<' -> replacedS.append('>');
                case '>' -> replacedS.append('<');
                case '[' -> replacedS.append(']');
                case ']' -> replacedS.append('[');
                case '{' -> replacedS.append('}');
                case '}' -> replacedS.append('{');
                case '(' -> replacedS.append(')');
                case ')' -> replacedS.append('(');
                case '/' -> replacedS.append("\\");
                case '\\' -> replacedS.append("/");
                default -> replacedS.append(s.charAt(i));
            }
        }
        return replacedS.reverse().toString();
    }

    /**
     * Objectives
     * In the last stage, your task is to horizontally mirror the text to the right of the center line. When you've got a
     * list of modified strings, print them out just like in the previous stage but with a different pattern:
     * <p>
     * {modified line} | {reversed modified line}
     * <p>
     * reversed modified line is a modified line printed backward. Replace the characters that are not horizontally
     * symmetrical with their horizontally-opposite chars: < to >, [ to ], { to }, ( to ), / to \, and vice versa.
     * Example 1
     * Input the file path:
     * > C:\ASCII_Animals\MooFolder\Cow.txt
     * ^__^ | ^__^
     * _______/(oo) | (oo)\_______
     * <p>
     * /(__) | (__)\       )\/\
     * | w----||     |     ||----w |
     * ||     ||     |     ||     ||
     */
    public static void main(String[] args) {

        System.out.println("Input the file path:");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

//        if (file.exists() && file.isFile()) {
//            straightMirrors(file);
//        } else System.out.println("File not found!");
        try {
            straightMirrors(Path.of(filePath));
        } catch (Throwable ex) {
            System.out.println("File not found!");
        }
    }
}
