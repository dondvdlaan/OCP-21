package dev.manyroads.maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Implement method sum. Iterate over values of the map and sum them
 * <p>
 * Sample Input 1:
 * 3
 * PROCESSING 10
 * HOLD 20
 * CLEARD 0
 * Explain code
 * <p>
 * Sample Output 1:
 * 30
 */
public class MainMap {

    static <T> Integer sum(Map<T, Integer> mapje) {
        return mapje.values().stream().reduce(0, Integer::sum);
    }
 // ALTERNATIVE
//    return map.values().stream().mapToInt(Integer::intValue).sum();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int it = Integer.parseInt(scanner.nextLine());
            System.out.println(it);
            Map<String, Integer> mapj = new HashMap<>();
            for (int i = 0; i < it; i++) {
                List<String> list = List.of(scanner.nextLine().split("\\s+"));
                mapj.put(list.getFirst(), Integer.parseInt(list.getLast()));
            }
            System.out.println(sum(mapj));
        }
    }
//    ALTERNATIVE
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        Map<String, Integer> m = new HashMap<>();
//        int size = scanner.nextInt();
//        for (int i = 0; i < size; ++i) {
//            String key = scanner.next();
//            int value = scanner.nextInt();
//            m.put(key, value);
//        }
//        int result = sum(Map.copyOf(m));
//        System.out.println(result);
}
