package dev.manyroads.lambdaexpressions;

import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        int number = 12241;
        var temp = ((number % 100) / 10);
        System.out.println(temp);
        int digit = temp % 10;

        System.out.println(digit);
    }
}
