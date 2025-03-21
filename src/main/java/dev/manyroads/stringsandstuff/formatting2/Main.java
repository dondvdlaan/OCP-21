package dev.manyroads.stringsandstuff.formatting2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int height = scanner.nextInt();
        System.out.println(String.format("Hello, my name is %s and I am %d cm tall.", name, height));
    }
}
