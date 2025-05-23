package dev.manyroads.projects.readabilityscore.stage3.example3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Main {
    public static double readabilityScore(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        long chars = charsCount(text);
        long words = wordCount(text);
        long sentences = sentenceCount(text);
        double readability = Math.round((4.71 * chars / words + 0.5 * words / sentences - 21.43) * 100.0) / 100.0;
        return Math.max(0.0, readability);   // if readability is less than 0, we return 0
    }

    public static long charsCount(String text) {
        if (text == null || text.isEmpty()) {
            return 0L;
        }
        var charPattern = Pattern.compile("\\S");
        var charMatcher = charPattern.matcher(text);
        return charMatcher.results().count();
    }

    public static long wordCount(String text) {
        if (text == null || text.isEmpty()) {
            return 0L;
        }
        var wordPattern = Pattern.compile("\\S+");
        var wordMatcher = wordPattern.matcher(text);
        return wordMatcher.results().count();
    }

    public static long sentenceCount(String text) {
        if (text == null || text.isEmpty()) {
            return 0L;
        }
        var sentencePattern = Pattern.compile("[^.?!]+");
        var sentenceMatcher = sentencePattern.matcher(text);
        return sentenceMatcher.results().count();
    }

    public static String ageBracket(int readabilityTruncated) {
        int lowerAge = readabilityTruncated + 5;
        int upperAge = readabilityTruncated > 13 ? 22 : readabilityTruncated + 6;
        return String.format("%d-%d", lowerAge, upperAge);
    }

    public static String formattedReadabilityData(String text) {
        String template = """
                Words: %d
                Sentences: %d
                Characters: %d
                The score is: %.2f
                This text should be understood by %s year-olds.""".stripIndent();
        if (text == null) {
            return template.formatted(0, 0, 0, 0.0, ageBracket(0));
        }
        return template.formatted(
                wordCount(text),
                sentenceCount(text),
                charsCount(text),
                readabilityScore(text),
                ageBracket((int) readabilityScore(text))
        );
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("You must provide a valid path for the file containing your text.");
            return;
        }
        try {
            final var path = Paths.get(args[0]);
            final var text = Files.readString(path);
            System.out.println("The text is:");
            System.out.println(text);
            System.out.println();
            System.out.println(formattedReadabilityData(text));
        } catch (InvalidPathException ipe) {
            System.out.println("Invalid path");
        } catch (IOException ioe) {
            System.out.println("Cannot read file");
        }
    }
}
