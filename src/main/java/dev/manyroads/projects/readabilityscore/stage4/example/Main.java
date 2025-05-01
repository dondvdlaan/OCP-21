package dev.manyroads.projects.readabilityscore.stage4.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static String[] AGES = {"6", "7", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "24", "24+"};

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            analyzeString(scanner.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    private static void analyzeString(String sentence) {
        double sentenceCount = sentence.split("[?.!]").length;
        double wordCount = sentence.split("\\s+").length;
        double charCount = sentence.chars().filter(item -> item != ' ' && item != '\n').count();
        double syllables = sentence.split("[aeiouy]+[^$e(,.:;!?)]").length;
        double polysyllables = sentence.split("([aeiouy]+[^$e(,.:;!?)]){3,}").length;
        double arIndex = 4.71 * (charCount / wordCount) + 0.5 * (wordCount / sentenceCount) - 21.43;
        double fkTestsIndex = 0.39 * (wordCount / sentenceCount) + 11.8 * (syllables / wordCount) - 15.59;
        double gobbledygookIndex = 1.043 * Math.sqrt(polysyllables * (30 / sentenceCount)) + 3.1291;
        double clIndex = 0.0588 * (charCount / wordCount * 100) - 0.296 * (sentenceCount / wordCount * 100) - 15.8;

        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Characters: " + charCount);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);

        System.out.println("Automated Readability Index: " + formatScore(arIndex));
        System.out.println("Flesch–Kincaid readability tests: " + formatScore(fkTestsIndex));
        System.out.println("Simple Measure of Gobbledygook: " + formatScore(gobbledygookIndex));
        System.out.println("Coleman–Liau index: " + formatScore(clIndex));
    }

    private static String formatScore(double score) {
        return score + " (about " + AGES[score <= 13 ? (int) score : 13] + " year olds).";
    }
}
