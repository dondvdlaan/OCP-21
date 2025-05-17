package dev.manyroads.streams.outputstreams;

import java.io.CharArrayWriter;

class Main extends Converter {

    public static void writeWords(String[] words) {
        LetterPrinter printer = new LetterPrinter();
        char[] letters = convert(words); // converting method
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
        CharArrayWriter writer = new CharArrayWriter();
        for (String s : words) {
            writer.append(s);
        }
        return writer.toCharArray();
    }
}
