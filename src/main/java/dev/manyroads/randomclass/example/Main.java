package dev.manyroads.randomclass.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        Random random;

        ArrayList<Integer> maxNums = new ArrayList<>();
        for (int i = a; i <= b; i++) {
            ArrayList<Integer> numbers = new ArrayList<>();
            random = new Random(i);
            for (int j = 0; j < n; j++) {
                numbers.add(random.nextInt(k));
            }
            maxNums.add(Collections.max(numbers));
        }

        int min = Collections.min(maxNums);
        int index = maxNums.indexOf(min);       // returns first occurence of lowest max
        System.out.println(a + index);
        System.out.println(maxNums.get(index));
    }
}
