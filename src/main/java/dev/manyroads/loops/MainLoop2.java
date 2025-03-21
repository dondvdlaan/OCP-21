package dev.manyroads.loops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Imagine that your company has a detector that evaluates the readiness of parts:
 * <p>
 * If the part it needs to be fixed, the detector prints the number 1.
 * If the part needs to be removed as a reject, the detector prints the number -1.
 * If the part is perfect and is ready to be sent, the detector prints 0.
 * <p>
 * You are given the logs produced by the detector.
 * The number n in the first line indicates the number of parts.
 * Write a program, which counts the total number of parts ready to be shipped,
 * the number of parts to be fixed, and the number of rejects. After that,
 * the program should output these numbers in that order in a single line,
 * separated by blank spaces (see the example, where n = 11).
 * <p>
 * Sample Input 1:
 * 11
 * 0
 * 0
 * -1
 * -1
 * 0
 * 0
 * 1
 * 0
 * 1
 * -1
 * 0
 * Sample Output 1:
 * 6 2 3
 */
public class MainLoop2 {
    final static String S = "0";
    final static String F = "1";
    final static String R = "-1";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nrElem = scanner.nextInt();
//        int f = 0, r = 0, s = 0;
//        for (int i = 0, elem; i < nrElem; i++) {
//            elem = scanner.nextInt();
//            switch (elem) {
//                case 1 -> f++;
//                case -1 -> r++;
//                case 0 -> s++;
//            }
//        }
//        System.out.println(s + " " + f + " " + r);

//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < nrElem; i++) {
//            list.add(scanner.nextInt());
//        }
//        System.out.printf("%d %d %d%n",
//                Collections.frequency(list, 0),
//                Collections.frequency(list, 1),
//                Collections.frequency(list, -1)
//        );

//        Map<String,Long> report = scanner.tokens()
//                .limit(nrElem)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//                System.out.printf("%d %d %d%n",
//                report.getOrDefault(S,0L),
//                report.getOrDefault(F,0L),
//                report.getOrDefault(R,0L)
//        );

        final Map<Integer, Long> groups = IntStream.generate(scanner::nextInt)
                .limit(nrElem)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.printf("%d %d %d",
                groups.getOrDefault(0, 0L),
                groups.getOrDefault(1, 0L),
                groups.getOrDefault(-1, 0L)
        );
    }
}
