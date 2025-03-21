package dev.manyroads.ifs;

import java.util.Scanner;

/**
 * Determining range of an integer: low, mid or high
 * Given an input integer 'n', your program must output a string. If 'n' is less than 5, output 'LOW'.
 * If 'n' is exactly 5, output 'MID'.
 * If 'n' is greater than 5, output 'HIGH'..
 */
public class MainIf4 {
    interface LowMidHigh {
        String determine(int nr);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(n < 5 ? "LOW" : n == 5 ? "MID" : "HIGH");
            LowMidHigh lmh = nr -> nr < 5 ? "LOW" : nr == 5 ? "MID" : "HIGH";
            System.out.println(lmh.determine(n));
        }
    }
}
