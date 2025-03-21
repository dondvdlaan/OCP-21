package dev.manyroads.loops;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Find the sum of numbers divisible by 6 in the given sequence of natural numbers.
 * <p>
 * The first line of the input is the number of elements in the sequence; the next lines are the elements themselves.
 * <p>
 * It is guaranteed that there is always a number divisible by 6 in the sequence.
 */
public class MainLoop {
    public static void main(String[] args) {
        //       Scanner scanner = new Scanner(System.in);
//        int nrElements = scanner.nextInt();
//        int tot = 0;
//        for (int i = 0, elem; i < nrElements; i++) {
//            elem = scanner.nextInt();
//            tot += elem % 6 == 0 ? elem : 0;
//        }
//        System.out.println(tot);

        final int divisor = 6;
        new Scanner(System.in)
                .tokens()
                .skip(1)
                .limit(12)
                .mapToInt(Integer::parseInt)
                .filter(i -> i % divisor == 0)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);

//        Scanner scanner = new Scanner(System.in);
//        System.out.println(Stream
//                .generate(scanner::nextInt)
//                .limit(scanner.nextInt())
//                .filter(i -> i % 6 == 0)
//                .reduce(0,Integer::sum));
    }
}
