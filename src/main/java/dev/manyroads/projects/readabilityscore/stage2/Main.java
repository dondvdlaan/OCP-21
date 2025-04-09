package dev.manyroads.projects.readabilityscore.stage2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Words and sentences
 * However, a real text may be pretty long and still easy to read, right? There needs to be another approach.
 * How about calculating the number of words in each sentence? Clearly, if each sentence of a text contains a
 * lot of words, this text is hard to read.
 * Suppose that if the text contains sentences that on average have more than 10 words per sentence, this text
 * is hard to read. Otherwise, this text is easy to read. Don't worry, we will consider more scientific ways in
 * the next stages.
 * The input contains a single line of text. Output "HARD" if the text is hard to read and "EASY" if the text
 * is easy to read.
 * <p>
 * For example, the first example contains two sentences with 6 and 10 words (numbers also count as words) so
 * the average is 8, and this is less than 10. In the second example, there are also 2 sentences but with 6
 * and 16 words, so the average is 11 and this is more than 10. If the average is equal to 10, the text is
 * still considered easy to read.
 * <p>
 * Don't forget that sentences can end with a full stop as well as with an exclamation mark and a question mark.
 * But the last sentence can be with or without a punctuation mark at the end.
 * Example
 * > This text is simple to read! It has on average less than 10 words per sentence.
 * EASY
 * > This text is hard to read. It contains a lot of sentences as well as a lot of words in each sentence
 * HARD
 */
public class Main {
    public static void main(String[] args) {
        new ReadabilityScore(new UI()).run();
    }
}

class ReadabilityScore implements Runnable {

    final static int WORDS_PER_LINE = 10;
    UI userInterface;

    public ReadabilityScore(UI userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public void run() {
//        String[] text = userInterface.readLine().split("\\s+");
//        checkScore(text);
        checkScore();
    }

    private void checkScore(String[] text) {
        String rexEOL = ".+[!?.]";
        List<Integer> wordsPerLine = new ArrayList<>();
        int temp = 0;

        for (String s : text) {
            temp++;
            if (s.matches(rexEOL)) {
                wordsPerLine.add(temp);
                temp = 0;
            }
        }
        if (temp > 0) wordsPerLine.add(temp);
        wordsPerLine.forEach(System.out::println);
        double avg = wordsPerLine.stream().mapToDouble(i -> i).average().orElse(0D);
        System.out.println(avg);
        System.out.println(avg > WORDS_PER_LINE ? Readability.HARD : Readability.EASY);
    }

    private void checkScore() {
        String rexEOL = "[!?.]";
        String whiteSpaces = "\\s+";
        List<Integer> wordsPerLine = new ArrayList<>();
        String text = userInterface.readLine();
        String[] words = text.trim().split(whiteSpaces);
        String[] sentences = text.trim().split(rexEOL);
        Arrays.stream(sentences).forEach(System.out::println);
        double avg = 1.0 * words.length / sentences.length;
//        wordsPerLine.forEach(System.out::println);
//        double avg = wordsPerLine.stream().mapToDouble(i -> i).average().orElse(0D);
//        System.out.println(avg);
       System.out.println(avg > WORDS_PER_LINE ? Readability.HARD : Readability.EASY);
       double avg2= Arrays.stream(text.trim().split(rexEOL))
               .mapToInt(String::length)
               .average()
               .orElse(0D);
       System.out.println(avg2 > WORDS_PER_LINE ? Readability.HARD : Readability.EASY);
    }

}

class UI {
    Supplier<String> userInput = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");
    Consumer<Readability> printResult = System.out::println;

    String readLine() {
        return userInput.get();
    }

}

enum Readability {
    EASY, HARD;
}
