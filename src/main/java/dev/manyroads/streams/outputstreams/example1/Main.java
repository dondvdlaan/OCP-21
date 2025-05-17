package dev.manyroads.streams.outputstreams.example1;

import java.io.CharArrayWriter;
import java.util.Arrays;

class Main extends Converter {

    public static void writeWords(String[] words) {
        LetterPrinter printer = new LetterPrinter();
        char[] letters = convert2(words); // converting method
        for (char letter : letters) {
            printer.write(letter);
        }

    }

    public static void main(String[] args) {
        String[] s = {"This", " ", "is", " ", "a", " ", "test"};
        writeWords(s);
    }
}

class LetterPrinter {
    public void write(char letter) {
        System.out.print(letter);
    }
}

class Converter {
    public static char[] convert(String[] words) {
        String s = String.join("", words);
        System.out.println(s);
        return s.toCharArray();
    }

    public static char[] convert2(String[] words) {
        return Arrays.stream(words)
                .reduce("", String::concat)
                .toCharArray();
    }
}

