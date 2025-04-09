package dev.manyroads.projects.readabilityscore.stage2.example1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] sentences = sc.nextLine().split("[!.?]");
        /*
        It's so he adds a space between sentences. For example, if the string were "Hello Word. I am alive.", the sentences array
        would be ["Hello World.", "I am alive."]. If he didn't do the String.join(), the words array would be
        ("Hello", "World.I", "am", alive"] which would give the wrong number of words, 4 instead of 5.
         */
        String[] words = String.join(" ", sentences).split("\\s+");
        String ans = 1.0 * words.length / sentences.length > 10 ? "HARD" : "EASY";

        System.out.println(ans);
    }
}
