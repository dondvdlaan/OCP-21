package dev.manyroads.projects.readabilityscore.stage1;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Simplest estimation
 * Ever wondered how to assess if the text is hard or easy to read? For a human, this task is pretty simple: you just read
 * the text and feel if you're struggling or not. But how to teach a computer do that? In this project, you will write
 * such a program.
 * <p>
 * Firstly, let's create a simple program. If a text contains more than 100 symbols (including spaces and punctuation),
 * then it is considered hard to read. Otherwise, the text is considered easy to read. If a text contains exactly
 * 100 symbols, it is still easy to read.
 * <p>
 * The input contains a single line of text. Output "HARD" if the text is hard to read and
 * "EASY" if the text is easy to read.
 * Example
 * > This text is easy to read!
 * EASY
 * > This text is hard to read. It contains a lot of sentences as well as a lot of words in each sentence.
 * HARD
 */
public class Main {
    public static void main(String[] args) {
        new ReadabilityScore(new UI()).run();
    }
}

class ReadabilityScore implements Runnable {

    final static int SYMBOLS = 100;
    final static String EASY = "EASY";
    UI userInterface;

    public ReadabilityScore(UI userInterface) {
        this.userInterface = userInterface;
    }


    @Override
    public void run() {
        String line = userInterface.readLine();
        checkScore(line);
    }

    private void checkScore(String line) {
        if (line.length() <= SYMBOLS) userInterface.printResult.accept(Readability.EASY);
        else userInterface.printResult.accept(Readability.HARD);
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
