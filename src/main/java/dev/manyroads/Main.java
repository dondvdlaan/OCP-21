package dev.manyroads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        String input = "John,25.00,Developer\nJane,30.00,Designer\nDoe,22.00,Intern";
        Scanner scanner = new Scanner(new File("purchases.txt")).useDelimiter(",|\\n");

        // Set the delimiter to comma and newline
        scanner.useDelimiter(",|\\n");

        while (scanner.hasNext()) {
            String cat = scanner.next();
            String desc = scanner.next();
            double price = scanner.nextDouble();
            System.out.println("Cat: " + cat + ", Desc: " + desc + ", Price: " + price );
        }

        scanner.close();
    }

}



