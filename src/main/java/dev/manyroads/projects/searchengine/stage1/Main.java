package dev.manyroads.projects.searchengine.stage1;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * search for a specific word in a multi-word input line.
 * <p>
 * The input line contains several words separated by a space. The words are numbered according to their order,
 * with the first word having index 1. Consider that all the words in the line are unique, so there can be no repetition.
 * <p>
 * Objectives
 * Write a simple program that reads two lines: a line of words and a line containing the search word. The program must
 * search in the first line for a word specified in the second one. The program should output the index of the specified
 * word. If there is no such word in the first line, the program should print Not Found. Please remember that indexes
 * start from 1!
 * <p>
 * Example
 * > first second third fourth
 * > third
 * 3
 */
public class Main {
    public static void main(String[] args) {
        new SearchEngine().searchWord2();
    }
}

class SearchEngine {

    Supplier<String> userImport = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");

    String searchWord() {
        String[] line = userImport.get().split(" ");
        String keyWord = userImport.get();
        long index = IntStream.rangeClosed(1, line.length).filter(i -> line[i - 1].equals(keyWord)).findFirst().orElse(0);
        return index > 0 ? Long.toString(index) : "Not Found";
    }

    void searchWord2() {
        String line = userImport.get();
        String keyWord = userImport.get();
        System.out.println(line.indexOf(keyWord));
        //long index = IntStream.rangeClosed(1, line.length).filter(i -> line[i - 1].equals(keyWord)).findFirst().orElse(0);
    }
}
