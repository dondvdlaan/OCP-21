package dev.manyroads.stringsandstuff.formatting;

/**
 * You are an employee of FakeCorp. You have been successfully imitating work and getting paid for it for several years.
 * Your boss has become suspicious. You've been tasked with using variables name, status, errorCode and methods printf
 * and format to display the following message:
 *
 * Sample Output 1:
 * Result of work - Lazy employee detected! Error code: 2
 */
public class Main {
    public static void main(String[] args) {
        String name = "Lazy";
        String status = "detected";
        int errorCode = 2;

        //Fix code below
        String formattedMessage = String.format("%s employee %s! Error code: %d", name, status, errorCode);
        System.out.printf("Result of work - %s", formattedMessage);

    }
}
