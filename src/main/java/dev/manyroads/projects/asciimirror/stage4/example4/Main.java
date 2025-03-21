package dev.manyroads.projects.asciimirror.stage4.example4;

import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.lang.Math;

public class Main {
    public static int maxlen (List<String> list) {
        int maxl = 0;
        for (String element : list) {
            maxl = Math.max(maxl, element.length());
        }
        return maxl;
    }

    public static String padRight (String s, int width) {
        return s+" ".repeat(width-s.length());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String fileName = scanner.nextLine();
        try {
            List<String> filecontent = Files.readAllLines(Paths.get(fileName));
            int maxLineLength = maxlen(filecontent);
            for (String element : filecontent) {
                System.out.print(padRight(element,maxLineLength));
                System.out.print(" | ");
                System.out.println(padRight(element,maxLineLength));
            }
        } catch (IOException e) {
            System.out.println("File not found!");
            return;
        }

    }
}
