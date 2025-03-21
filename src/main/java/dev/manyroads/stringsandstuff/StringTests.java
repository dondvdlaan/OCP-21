package dev.manyroads.stringsandstuff;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringTests {

    static String composeMulipleLineString() {
        return """
                Line compose by text blocks
                SELECT * FROM Customer c 
                WHERE c.first_name iLIKE "_Don%;
                <p> Html text block</p>
                """;
    }

    static String composeWithTextBlocks() {
        return """
                a
                 b
                c""";
    }

    static String composeWithStringBuilder() {

        return new StringBuilder()
                .append("fisrt line\n")
                .append("second line")
                .toString()
                ;
    }

    static String composeWithConcate() {

        return new String("composeWithConcate\n")
                .concat("fisrts line\n")
                .concat("secondline")
                ;
    }

    static String composeWithJoin() {

        return String.join("\n", "composeWithJoin", "first line", "cond line")
                ;
    }

    static String composeWithJStream() {

        return Stream.of("composeWithJStream", "first line", "cond line").collect(Collectors.joining(String.valueOf("\n")));
    }

    static String composeWithTextBlockFormatted(int a, int b) {
        return """
                Tesje a: %d
                Testje b: %d
                """.formatted(a, b);
    }

    public static void main(String[] args) {
        System.out.println(composeMulipleLineString());
        System.out.println(composeWithStringBuilder());
        System.out.println(composeWithConcate());
        System.out.println(composeWithJoin());
        System.out.println(composeWithJStream());
        System.out.printf("The \n  has to be counted for 1 char, totalling; %d length%n", composeWithTextBlocks().length());
        System.out.println(composeWithTextBlockFormatted(12,45));
    }
}
