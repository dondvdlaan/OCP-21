package dev.manyroads.projects.readabilityscore.stage2.example2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        sc.close();

        String[] sentences = text.split("[!.?]");
        String[] words = text.split("\\s+");
        boolean isHard = (float) words.length / sentences.length > 10;

        System.out.println(isHard ? "HARD" : "EASY");
    }
}
