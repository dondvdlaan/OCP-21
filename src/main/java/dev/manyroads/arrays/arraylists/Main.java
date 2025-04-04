package dev.manyroads.arrays.arraylists;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

/**
 * Write a program that finds the elements in an array of integers that are closest to a given integer.
 * If you find several integers with the same distance to N, you should output all of them in ascending order.
 * If there are several equal numbers, output them all.
 * <p>
 * Input: a set of integers and a number N.
 * <p>
 * Output: some number(s) from the given array.
 * Sample Input 1:
 * 1 2 4 5
 * 3
 * Sample Output 1:
 * 2 4
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int N = scanner.nextInt();
        int indexMin = IntStream.range(0, a.length).map(i -> abs(a[i] - N)).min().orElse(-1);
        if(indexMin!= -1){
            IntStream.range(0, a.length).filter(i -> abs(a[i] - N)==indexMin)
                    .map(i->a[i])
                    .sorted()
                    .forEach(x->System.out.print(x + " "));
        }
//        Arrays.stream(numbers)
//                .filter(i -> Math.abs(n - i) == minDelta)
//                .sorted()
//                .forEach(i -> System.out.print(i + " "));
    }
}
