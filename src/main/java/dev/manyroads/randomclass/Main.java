package dev.manyroads.randomclass;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int N = sc.nextInt();
        int K = sc.nextInt();
        Map<Integer, Integer> map = new HashMap<>();
        for (int seed = A; seed <= B; seed++) {
            Random r = new Random(seed);
            int max = IntStream.range(0, N).map((x) -> r.nextInt(K)).max().orElse(-1);
            map.put(seed, max);
        }

        int minMax = map.values().stream().min(Integer::compare).orElse(-1);
        Map<Integer, Integer>
                filtered = map.entrySet()
                .stream()
                .filter(a -> a.getValue() == minMax)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (filtered.values().size() > 1) {
            int minSeed = filtered.keySet().stream().min(Integer::compare).orElse(-1);
            System.out.println(minSeed);
        } else filtered.keySet().forEach(System.out::println);
        System.out.println(minMax);
    }
}