package dev.manyroads.loops;

import java.util.Scanner;
import java.util.stream.IntStream;

public class MainLoop4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int p = 1;
        for (int i = a; i < b; i++) {
            p *= i;
        }
        System.out.println(p);

        IntStream.range(a, b).reduce((x, y) -> x * y).ifPresent(System.out::println);
    }

}
