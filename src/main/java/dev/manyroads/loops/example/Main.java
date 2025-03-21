package dev.manyroads.loops.example;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final int divisor = 6;
        new Scanner(System.in)
                .tokens()
                .skip(1)
                .mapToInt(Integer::parseInt)
                .filter(i -> i % divisor == 0)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);
    }
}
