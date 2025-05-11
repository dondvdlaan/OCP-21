package dev.manyroads.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque();
        Scanner scanner = new Scanner(System.in);
        int tot = scanner.nextInt();
        for (int i = 0; i < tot; i++) {
            stack.addLast(scanner.nextInt());
        }
//        for (int i = 0; i < tot; i++) {
//            System.out.println(stack.pollLast());
//        }
        while(!stack.isEmpty()) System.out.println(stack.pollLast());

        // alternative 1
        new Scanner(System.in).tokens()
                .skip(1).collect(Collectors.toCollection(ArrayDeque::new))
                .descendingIterator()
                .forEachRemaining(System.out::println);

        //Aleternative 2
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        Deque<Integer> items = new ArrayDeque<Integer>(size);
        for (; size > 0; size--) items.offer(sc.nextInt());
        while (!items.isEmpty()) System.out.println(items.pollLast());
        sc.close();

        // alternative 3
        Deque<String> stack2 = new ArrayDeque<>();
        scanner.tokens().skip(1).forEach(stack2::offerFirst);
        stack2.forEach(System.out::println);
    }
}
