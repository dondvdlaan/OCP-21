package dev.manyroads.projects.asciimirror.stage2;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String text= """
                            ^__^
                    _______/(oo)
                /\\/(       /(__)
                   | w----||   \s
                   ||     ||   \s
                """;
        System.out.println("Input the file path:");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        System.out.println(filePath);
        System.out.println(text);
    }
}
