package dev.manyroads.randomclass.nextgaussian.example2;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        final long k = scanner.nextLong();
        final long n = scanner.nextLong();
        final double m = scanner.nextDouble();
        long seed = k;
        Random rand = new Random(seed);
        while (Stream.generate(rand::nextGaussian).limit(n).anyMatch(i -> i > m)) {
            rand.setSeed(++seed);
        }
        System.out.println(seed);
    }
}
