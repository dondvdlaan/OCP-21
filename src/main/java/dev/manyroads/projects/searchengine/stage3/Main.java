package dev.manyroads.projects.searchengine.stage3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Objectives
 * Write a program that reads text lines from the standard input and processes single-word queries.
 * The program must output all lines that contain the string from the query. For this stage, this should
 * include the case where the query string appears as a substring of one of the text lines. For example,
 * the query "bc" should be found in a line containing "abcd".
 * <p>
 * You may choose what the text represents in your project. For example, each line may describe:
 * a person represented by the first name, the last name, and optionally an email;
 * an address of a building represented by the country, city, state, street, and zip code;
 * a book represented by its ISBN, title, author/authors, publisher, and so on.
 * <p>
 * You can use any of these options or come up with your own, because your search algorithm should work
 * regardless of what the text actually represents.
 * <p>
 * Here is an example of a line. It contains three items: first name, last name, and this person's email.
 * Elsa Sanders elsa@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        new SearchEngine(new Repository<>(),new UI()).run();
    }
}


