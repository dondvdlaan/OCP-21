package dev.manyroads;


import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] x) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        double p = (a + b + c) / 2.0;
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        System.out.println(s);

    }


}

