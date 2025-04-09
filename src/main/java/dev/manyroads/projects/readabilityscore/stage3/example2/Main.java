package dev.manyroads.projects.readabilityscore.stage3.example2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <filename>");
            return;
        }

        String filename = args[0];
        StringBuilder text = new StringBuilder();

        try {
            Files.lines(Paths.get(filename)).forEach(line -> text.append(line).append("\n"));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        String input = text.toString();
        System.out.println("The text is:");
        System.out.println(input);

        // Split the input into sentences
        String[] sentences = input.split("[.!?]+\\s*");
        int sentenceCount = sentences.length;

        // Split the input into words
        String[] words = input.split("\\s+");
        int wordCount = words.length;

        // Count characters
        int charCount = 0;
        for (char c : input.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                charCount++;
            }
        }

        // Calculate the ARI score
        double score = 4.71 * ((double) charCount / wordCount) + 0.5 * ((double) wordCount / sentenceCount) - 21.43;
        int roundedScore = (int) Math.ceil(score);

        // Define the age brackets
        String[] ageBrackets = {
                "5-6", "6-7", "7-8", "8-9", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", "15-16", "16-17", "17-18", "18-22", "24+"
        };

        String ageBracket = roundedScore >= 1 && roundedScore <= 14 ? ageBrackets[roundedScore - 1] : "24+";

        // Print the results
        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Characters: " + charCount);
        System.out.println("The score is: " + score);
        System.out.println("This text should be understood by " + ageBracket + " year-olds.");
    }
}
