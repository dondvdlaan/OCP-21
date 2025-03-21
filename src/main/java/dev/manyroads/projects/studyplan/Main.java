package dev.manyroads.projects.studyplan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;


public class Main {

    /**
     * Write a program that reads three integer numbers and prints true if the first number is between
     * the second and the third one (inclusive). Otherwise, it must print false.
     * The last two arguments may not be sorted.
     * Sample Input 1:
     * 3 3 3
     * true
     * 2 7 9
     * false
     */
    static void isBetween() {
        Scanner scanner = new Scanner(System.in);
        int f = scanner.nextInt();
        int s = scanner.nextInt();
        int t = scanner.nextInt();
        System.out.println((f >= s && f <= t) || (f <= s && f >= t));
    }

    /**
     * Write a program that takes two integers as the input: the beginning and the end of the interval (both numbers belong to the interval).
     * <p>
     * The program should output the numbers from this interval, but if the number is divisible by 3, you should output Fizz instead of it;
     * if the number is divisible by 5, output Buzz; and if it is divisible both by 3 and by 5, output FizzBuzz.
     * <p>
     * Output each number or the word on a separate line.
     */
    static void fizzBuzz() {
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        IntStream.rangeClosed(start, end).mapToObj(n -> {
            return (n % 3 == 0) && (n % 5 == 0) ? "FizzBuzz" : n % 5 == 0 ? "Buzz" : n % 3 == 0 ? "Fizz" : Integer.toString(n);
        }).forEach(System.out::println);
    }

    /**
     * A bus tour of Europe has been very successful. Due to an increase in the number of people who want to go on a tour,
     * the tour company decided to increase the height of the bus. The new height of the bus is exactly N centimeters.
     * But the tour’s route runs under a lot of bridges, and there is a chance that the bus will crash into one of these bridges.
     * Can you find out if this will happen?
     * The first line of the input contains the height of the bus and the number of bridges under which the bus passes.
     * The second line contains the heights of these bridges.
     * You should output "Will not crash" if everything will be alright; otherwise, output "Will crash on bridge i"
     * (where i is the number of the bridge) into which the bus will crash. If the height of a bridge equals the height of the bus,
     * the bus will crash.
     * Input
     * 243 8
     * 465 453 981 463 1235 871 475 981
     * Output
     * Will not crash
     * Input
     * 211 5
     * 871 205 123 871 1681
     * Output
     * Will crash on bridge 2
     */
    static void busTour() {
        Scanner scanner = new Scanner(System.in);
        final int heightBus = scanner.nextInt();
        int nrBridges = scanner.nextInt();
        final int[] heightBridges = IntStream.rangeClosed(1, nrBridges).map(n -> scanner.nextInt()).toArray();
        int[] crashes = IntStream.range(0, nrBridges).filter(i -> heightBridges[i] <= heightBus).toArray();
        System.out.println(crashes.length > 0 ? "Will crash on bridge " + (crashes[0] + 1) : "Will not crash");

    }


    public static void main(String[] args) {
        //fizzBuzz();
        busTour();
    }
}
