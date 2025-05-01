package dev.manyroads.randomclass.nextgaussian;

import java.util.Random;
import java.util.Scanner;

/**
 * Gaussian random numbers
 * For the given numbers K, N and M, begin iterating through the various seeds starting from K: (K, K+1, K+2...).
 * In each iteration, use that seed for a random generator and get N random Gaussian numbers using the Random.nextGaussian() method.
 * Output the seed for which all N Gaussian numbers are less than or equal to M.
 * <p>
 * The input contains numbers K, N, M in a single line.
 * 0 5 0
 * Sample Output 1:
 * 38
 */
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();

        boolean done = false;
        int seed = k;
        while (!done) {
            Random random = new Random(seed);
            for (int i = 0; i < n; i++) {
                double gausNr = random.nextGaussian();
                if (gausNr > m) break;
                if (i == n - 1) done = true;
            }
            if (done) System.out.println(seed);
            else seed++;
        }
    }

}
