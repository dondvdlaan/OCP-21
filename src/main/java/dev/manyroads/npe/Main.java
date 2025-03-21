package dev.manyroads.npe;

import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    /* Fix this method */
    public static String toUpperCase(String str) {

        return Optional.ofNullable(str).map(s->s.toUpperCase(Locale.ENGLISH)).orElse("");
    }

    public static void printLength(String  name) {
        Optional.ofNullable(name).ifPresent(n -> System.out.println(n.length()));

    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        line = "none".equalsIgnoreCase(line) ? null : line;
        System.out.println(toUpperCase((line)));
        printLength(line);
    }
}

class FixingNullPointerException {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        string = "null".equals(string) ? null : string;
        /* Do not change code above */

        System.out.println(Optional.ofNullable(string).map(String::toLowerCase).orElse("NPE!"));
    }
}
