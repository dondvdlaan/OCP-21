package dev.manyroads.ifs;

import java.util.Scanner;

/**
 * Given three natural numbers A, B, and C. Determine if a triangle with these sides can exist.
 * If the triangle exists, output the "YES" string; otherwise, output "NO".
 * A triangle is valid if the sum of its two sides is greater than the third side.
 * If three sides are A, B, and C, then three conditions should be met.
 * <p>
 * 1. A + B > C
 * 2. A + C > B
 * 3. B + C > A
 */
public class MainIf5 {
    interface Triangle {
        String determine(int a, int b, int c);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            int C = scanner.nextInt();
            Triangle triangle = (a, b, c) -> a + b > c && a + c > b && b + c > a ? "YES" : "NO";
            System.out.println(triangle.determine(A, B, C));
        }
    }
}
