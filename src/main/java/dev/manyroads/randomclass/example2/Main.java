package dev.manyroads.randomclass.example2;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int seedMin = a;
        Integer max = null;

        for (int seed = a; seed <= b; seed++) {
            Integer curMax = null;
            Random random = new Random(seed);

            for (int i = 0; i < n; i++) {
                int nextVal = random.nextInt(k);
                if (curMax == null || nextVal > curMax) {
                    curMax = nextVal;
                }
            }

            if (max == null || curMax < max) {
                max = curMax;
                seedMin = seed;
            }
        }

        System.out.println(seedMin);
        System.out.println(max);
    }
}