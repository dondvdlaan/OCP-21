package dev.manyroads.streams.examples;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Check if an array is sorted ascending
 * Write a program that reads an array of int's and checks if the array is sorted ascending
 * (from smallest to largest number).
 * <p>
 * Input data format
 * The first line contains the size of an array.
 * The second line contains elements of the array separated by spaces.
 * Sample Input 1:
 * 4
 * 1 2 3 4
 * Sample Output 1:
 * true
 * 4
 * 4 5 2 7
 * false
 */
public class MainStreams {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int nrElem = scanner.nextInt();
        final int[] ints = IntStream.generate(scanner::nextInt).limit(nrElem).toArray();
        System.out.println(IntStream.range(0, nrElem - 1).allMatch(i -> ints[i + 1] > ints[i]));
    }
}

/**
 * Sum array elements greater than a value
 * Write a program that reads an array of ints and an integer number n.
 * The program must sum all the array elements greater than n.
 * <p>
 * Input data format
 * The first line contains the size of an array.
 * The second line contains the elements of the array separated by spaces.
 * The third line contains the number n.
 * <p>
 * Output data format
 * Only a single number representing the sum.
 * <p>
 * Sample Input 1:
 * 5
 * 5 8 11 2 10
 * 8
 * Sample Output 1:
 * 21
 */
class Test7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nrElem = scanner.nextInt();
        final int[] ints = scanner.tokens().limit(nrElem).mapToInt(Integer::parseInt).toArray();
        //final int[] ints = IntStream.generate(scanner::nextInt).limit(nrElem).toArray();
        final int n = scanner.nextInt();
        int sum = 0;
        for (int i = 0, res; i < nrElem; i++) {
            sum += ints[i] > n ? ints[i] : 0;
        }
        System.out.println(sum);
        Arrays.stream(ints).filter(nr -> nr > n).reduce(Integer::sum).ifPresent(System.out::println);
        System.out.println(Arrays.stream(ints).filter(nr -> nr > n).sum());
    }
}

/**
 * Reverse elements
 * In this task, you need to implement reverseElements method. It should reverse all rows of the
 * twoDimArray as in the example below.
 * 0 0 9 9              9 9 0 0
 * 1 2 3 4 will become: 4 3 2 1
 * 5 6 7 8              8 7 6 5
 * It is guaranteed that twoDimArray has at least 1 row.
 * <p>
 * P.S. You don't need to print anything in this task or create a new array: just modify the existing twoDimArray.
 */
class Test8 {
    static void printMat(int[][] mat) {
        System.out.println();
        for (int[] row : mat) {
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    // Alternative
    static void reverseElements2(int[][] twoDimArray) {
        for (int k = 0; k < twoDimArray.length; k++) {
            twoDimArray[k] = reverseOneDArray(twoDimArray[k]);
        }
    }

    static int[] reverseOneDArray(int[] oneDimArray) {
        int len = oneDimArray.length;
        return IntStream.range(0, len).map(i -> oneDimArray[len - 1 - i]).toArray();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] twoDimArray = new int[n][m];
        for (int k = 0; k < twoDimArray.length; k++) {
            for (int l = 0; l < twoDimArray[k].length; l++) {
                twoDimArray[k][l] = scanner.nextInt();
            }
        }
        scanner.close();
        printMat(twoDimArray);
        reverseElements2(twoDimArray);
        //reverseElements(twoDimArray);
        printMat(twoDimArray);
    }
}
