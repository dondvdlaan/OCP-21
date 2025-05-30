package dev.manyroads.streams.examples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

class Test9 {
    static List<Integer> c(List<Integer> l1, List<Integer> l2) {
        l1.addAll(l2);
        l1.removeIf(i -> i < 0);
        return l1;
    }

    public static List<Integer> concatPositiveNumbers(List<Integer> l1, List<Integer> l2) {
        return Stream
                .concat(l1.stream(), l2.stream())
                .filter(x -> x > 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(-1);
        list1.add(2);
        list1.add(-3);
        list1.add(4);
        List<Integer> list2 = new ArrayList<>();
        list1.add(4);
        list1.add(-5);
        list1.add(6);
        list1.add(-7);

        System.out.println(c(list1, list2));
        System.out.println(concatPositiveNumbers(list1, list2));
    }
}

class test10 {
    static int rotation = 0;
    static int rowCounter = 0;

    public static void main(String[] args) {
//        int exp1 = rowCounter * (cols - 1);
//        int exp2 = (rows - 1) * (cols - 1);
//
//        Integer[][] piece = null;
//        !Arrays.stream(piece[rotation]).allMatch(pos -> (pos + exp1) < exp2); // returns boolean
    }
}

/**
 * Comparing two arrays
 */
class test11 {
    public static void main(String[] args) throws IOException {
        Integer[] staticBoard = {1, 2, 3, 4};
        Integer[][] piece = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        int rotation = 1;
        boolean temp = Arrays.stream(staticBoard)
                .anyMatch(statPos -> Arrays.asList(piece[rotation]).contains(statPos));
        System.out.println(temp);
    }
}

class test12{
    public static void main(String[] args) {
//        Integer[] temp2 = temp.stream()
//                .map(i -> {
//                    if (!Objects.equals(i, -1)) return i + cols;
//                    return i;
//                }).toArray(Integer[]::new);
    }
}

class test13{
    public static String getDayOfWeekName(int number) {
        return Map.of(1, "Mon", 2,  "Tue", 3,  "Wed", 4,  "Thu", 5,  "Fri", 6, "Sat", 7, "Sun").entrySet().stream()
                .filter(e -> e.getKey() == number)
                .map(Map.Entry::getValue)
                .findAny().orElseThrow(IllegalArgumentException::new);
    }
}

class test14{
    enum Day {
        Mon(1), Tue(2), Wed(3), Thu(4), Fri(5), Sat(6), Sun(7);

        private final int number;

        Day(int number) {
            this.number = number;
        }
    }

    public static String getDayOfWeekName(int number) {
        return Arrays.stream(Day.values())
                .filter(d -> d.number == number)
                .findFirst()
                .map(String::valueOf)
                .orElseThrow(IllegalArgumentException::new);
    }
}

class test15{
//    var scanner = new Scanner(System.in);
//    var date = LocalDate.parse(scanner.nextLine());
//    int offset = scanner.nextInt();
//    int year = date.getYear();
//    UnaryOperator<LocalDate> nextDate = d -> d.plusDays(offset);
//        Stream.iterate(date, d -> d.getYear() == year, nextDate)
//            .forEach(System.out::println);
}

class test16{


    class Main {
        private static final int DAYS_PER_WEEK = 7;

        public static void main(String[] args) {
//            Scanner scanner = new Scanner(System.in);
//            int year = scanner.nextInt();
//            int month = scanner.nextInt();
//
//            LocalDate date = LocalDate.of(year, month, 1)
//                    .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//            while (date.getMonthValue() == month) {
//                System.out.println(date);
//                date = date.plusDays(DAYS_PER_WEEK);
            }
        }
    }
    class test17{
    // The sum of integers from a to b
//    IntStream.rangeClosed(scanner.nextInt(), scanner.nextInt())
//            .reduce(Integer::sum)
//                .ifPresent(System.out::println);

        // alternative
//        int a = sc.nextInt();
//        int b = sc.nextInt();
//
//        int ans = b*(b+1)/2 - a*(a-1)/2;
//
//        System.out.println(ans);
    }
    class Test18 {
        /**
         * calculates the sum of the elements of an array of ints
         *
         * @param args
         */
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int size = scanner.nextInt();
            scanner.tokens().limit(size).mapToInt(Integer::parseInt).reduce(Integer::sum).ifPresent(System.out::println);
        }
        // of..
//        int size = scanner.nextInt();
//        int[] ints = new int[size];
//        ints = scanner.tokens().limit(size).mapToInt(Integer::parseInt).toArray();
//        Arrays.stream(ints).reduce(Integer::sum).ifPresent(System.out::println);

        // of
//        int listSize = sc.nextInt();
//        List<Integer> list = IntStream.range(0, listSize).mapToObj(i -> sc.nextInt()).toList();
//            System.out.println(list.stream().mapToInt(Integer::intValue).sum());
    }
    class Test19{
//        RegistrerenAbonnement registrerenAbonnement = new RegistrerenAbonnement();
//        cjibNummers.forEach(cjibNummer -> {
//            registrerenAbonnement.getAbonnementDTO().add(maakAbonnementDto(cjibNummer));
//        });
    }

    class Test20{
        public static final TernaryIntPredicate ALL_DIFFERENT = numbers ->
                IntStream.of(numbers).distinct().count() == numbers.length;

        @FunctionalInterface
        public interface TernaryIntPredicate {
            boolean test(int... numbers);
        }
    }
    class Test21{
    // print longest word
//        var words = new Scanner(System.in).tokens()
//                .sorted(Comparator.comparing(String::length).reversed()).toArray();
//        System.out.print(words[0]);
//        - longest word in sentence
//        Scanner scanner = new Scanner(System.in);
//        String sentence = scanner.nextLine();
//
//        String longest = Arrays.stream(sentence.split(" "))
//                .max(Comparator.comparingInt(String::length)).get();
//        System.out.println(longest);
//
//- or
//        public static void main(String[] args) {
//            var words = new Scanner(System.in).tokens()
//                    .sorted(Comparator.comparing(String::length).reversed()).toArray();
//            System.out.print(words[0]);
//        }
//           System.out.println(words[index]);
//        var l = Stream.of(words).max(Comparator.comparing(String::length)).orElse("");
//            System.out.println(l);
    }
