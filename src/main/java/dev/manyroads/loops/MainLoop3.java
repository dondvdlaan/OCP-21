package dev.manyroads.loops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Find the number of D, C, B and A grades for the last test on informatics,
 * where n students from a class have successfully passed the test.
 * The program gets number n as the first line of input. This means there will be n number of grades.
 * Then the program gets the grades themselves, each on a new line.
 * <p>
 * Create a program to count the occurrence of each grade and output four numbers in a single line:
 * the number of D, C, B, and A grades (in that order), separated by blank space characters.
 * Sample Input 1:
 * 13
 * B
 * C
 * D
 * C
 * B
 * C
 * D
 * D
 * B
 * C
 * C
 * B
 * A
 * Sample Output 1:
 * 3 5 4 1
 */
public class MainLoop3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nrElem = scanner.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < nrElem; i++) {
            list.add(scanner.next());
        }
        System.out.printf("%d %d %d %d",
                Collections.frequency(list,"D"),
                Collections.frequency(list,"C"),
                Collections.frequency(list,"B"),
                Collections.frequency(list,"A")
        );

//        Map<String, Long> mapje = scanner.tokens()
//                .limit(nrElem)
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//                System.out.printf("%d %d %d %d",
//                mapje.getOrDefault("D",0L),
//                mapje.getOrDefault("C",0L),
//                mapje.getOrDefault("B",0L),
//                mapje.getOrDefault("A",0L)
//        );
    }
}
