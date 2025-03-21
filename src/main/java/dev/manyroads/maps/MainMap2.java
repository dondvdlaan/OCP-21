package dev.manyroads.maps;

import java.util.Map;
import java.util.Scanner;

/**
 * Implement the method createStatuses. Create and return a map with the following pairs: SUCCESS: 0, FAIL: 1, WARN: 2
 * Sample Input 1:
 * SUCCESS
 * Sample Output 1:
 * 0
 */
public class MainMap2 {

    static Map<String, Integer> createStatuses() {
        return Map.of("SUCCESS", 0, "FAIL", 1, "WARN", 2);
    }

    public static void main(String[] args) {


        try (Scanner scanner = new Scanner(System.in)) {
            String sInput = scanner.nextLine();
            System.out.println(createStatuses().getOrDefault(sInput,-1));
        }
    }
}
