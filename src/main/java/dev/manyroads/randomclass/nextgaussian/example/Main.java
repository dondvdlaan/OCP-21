package dev.manyroads.randomclass.nextgaussian.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        double n = scanner.nextDouble();
        double m = scanner.nextDouble();
        while (!isFound(k, n, m)) {
            k++;
        }
        System.out.println(k);
    }

    static boolean isFound(int k, double n, double m) {
        Random r = new Random(k);
        for (int i = 0; i < n; i++) {
            if (r.nextGaussian() > m) {
                return false;
            }
        }
        return true;
    }
}
