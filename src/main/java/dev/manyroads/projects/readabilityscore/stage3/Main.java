package dev.manyroads.projects.readabilityscore.stage3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Score!
 * Description
 * In this stage, you will program the Automated readability index (ARI). It was introduced in 1968 and a lot of research
 * works rely on this. The index is calculated by the following formula:
 * <p>
 * Below you can see the table that specifies the age brackets.
 * <p>
 * Also, your program should read a file instead of typing a text manually. You should pass the filename through the
 * command line arguments.
 * The program should output the score itself and an approximate age needed to comprehend the text.
 * Use the appropriate rounding function to calculate the score as integer.
 * You should also print how many characters, words, and sentences the text has.
 * The number of characters is any visible symbol (so, in the real text it's everything except space, newline "\n" and tab "\t").
 * <p>
 * Example
 * > java Main in.txt
 * The text is:
 * Readability is the ease with which a reader can understand a written text.
 * In natural language, the readability of text depends on its content and its presentation.
 * Researchers have used various factors to measure readability. Readability is more than simply legibility,
 * which is a measure of how easily a reader can distinguish individual letters or characters from each other.
 * Higher readability eases reading effort and speed for any reader, but it is especially important for those
 * who do not have high reading comprehension. In readers with poor reading comprehension, raising the readability
 * level of a text from mediocre to good can make the difference between success and failure
 * <p>
 * Words: 108
 * Sentences: 6
 * Characters: 580
 * The score is: 12.86
 * This text should be understood by 17-18 year-olds.
 * <p>
 * Example 2
 * > java Main in.txt
 * The text is:
 * This is the page of the Simple English Wikipedia. A place where people work together to write encyclopedias in
 * different languages. That includes children and adults who are learning English. There are 142,262 articles on
 * the Simple English Wikipedia. All of the pages are free to use. They have all been published under both the
 * Creative Commons License 3 and the GNU Free Documentation License. You can help here! You may change these pages
 * and make new pages. Read the help pages and other good pages to learn how to write pages here. You may ask
 * questions at Simple talk.
 * <p>
 * Words: 100
 * Sentences: 10
 * Characters: 476
 * The score is: 5.98
 * This text should be understood by 10-11 year-olds.
 */
public class Main {
    public static void main(String[] args) {
        String text = new UI().readFile(Path.of(args[0]));
        new ReadabilityScore().checkScore(text);
    }
}

class ReadabilityScore implements Runnable {

    Map<Long, List<String>> tableAgeBrackets;

    public ReadabilityScore() {
        this.tableAgeBrackets = getTableAgeBrackets();
    }

    @Override
    public void run() {

    }

    private double getAutomatedReadabilityIndex(int chrs, int words, int sentences) {
        if (!(words > 0) || !(sentences > 0)) {
            System.out.println("Can not divide by zero");
            return 0D;
        }
        return 4.71 * chrs / words + 0.5 * words / sentences - 21.43;
    }

    private Map<Long, List<String>> getTableAgeBrackets() {
        Map<Long, List<String>> tableAgeBrackets = new HashMap<>();
        tableAgeBrackets.put(1L, List.of("5-6", "Kindergarten"));
        tableAgeBrackets.put(2L, List.of("6-7", "First Grade"));
        tableAgeBrackets.put(3L, List.of("7-8", "Second Grade"));
        tableAgeBrackets.put(4L, List.of("8-9", "Third Grade"));
        tableAgeBrackets.put(5L, List.of("9-10", "Fourth Grade"));
        tableAgeBrackets.put(6L, List.of("10-11", "Fifth Grade"));
        tableAgeBrackets.put(7L, List.of("11-12", "Sixth Grade"));
        tableAgeBrackets.put(8L, List.of("12-13", "Seventh Grade"));
        tableAgeBrackets.put(9L, List.of("13-14", "Eighth Grade"));
        tableAgeBrackets.put(10L, List.of("14-15", "Ninth Grade"));
        tableAgeBrackets.put(11L, List.of("15-16", "tenth Grade"));
        tableAgeBrackets.put(12L, List.of("16-17", "Eleventh Grade"));
        tableAgeBrackets.put(13L, List.of("17-18", "Twelfth Grade"));
        tableAgeBrackets.put(14L, List.of("18-22", "College student"));
        return tableAgeBrackets;
    }

    public void checkScore(String text) {
        String rexEOL = "[!?.]";
        String whiteSpace = "\\s+";
        String[] words = Arrays.stream(text.trim().split(whiteSpace)).toArray(String[]::new);
        String[] sentences = text.trim().split(rexEOL);
        int chrs = Arrays.stream(words).map(String::length).reduce(Integer::sum).orElse(0);
        double score = getAutomatedReadabilityIndex(chrs, words.length, sentences.length);
        String age = tableAgeBrackets.get((long) Math.ceil(score)).getFirst();
        UI.printScore(words.length, sentences.length, chrs, score, age);
    }
}

class UI {
    Supplier<String> userInput = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");
    Consumer<Readability> printResult = System.out::println;

    String readLine() {
        return userInput.get();
    }

    String readFile(Path pathToFile) {
        try {
            return new String(Files.readAllBytes(pathToFile));
        } catch (SecurityException sec) {
            System.out.println("Security issue with file: " + sec.getMessage());
        } catch (IOException io) {
            System.out.println("Reading issue with file: " + io.getMessage());
        }
        return "";
    }

    static void printScore(int words, int sentencesm, int chrs, double score, String age) {
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentencesm);
        System.out.println("Characters: " + chrs);
        System.out.println(String.format("The score is: %.2f", score));
        System.out.printf("This text should be understood by %s year-olds.", age);
    }
}

enum Readability {
    EASY, HARD;
}
