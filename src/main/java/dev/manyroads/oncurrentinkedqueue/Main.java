package dev.manyroads.oncurrentinkedqueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

/**
 * Implement a method that accepts a queue and returns the last element of it.
 */
public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        addNumberToQueue(queue);
        //System.out.println(returnLastNumber(queue));
        //System.out.println(getLastNumber(queue));
        //System.out.println(getLastNumberII(queue));
        System.out.println(getLastNumberIII(queue));
    }

    static void addNumberToQueue(Queue<Integer> queue) {
        IntStream.range(1, 10).forEach(queue::add);
        System.out.println(queue);
    }

    static int returnLastNumber(Queue<Integer> target) {
        int result = 0;
        while (!target.isEmpty()) {
            result = target.poll();
        }
        return result;
    }

    public static int getLastNumber(Queue<Integer> target) {
        return target.stream()
                .reduce((first, second) -> second)
                .get();
    }

    public static int getLastNumberII(Queue<Integer> target) {
        return target.stream().toList().getLast();
    }

    public static int getLastNumberIII(Queue<Integer> target) {
        int last=0;
        while(target.iterator().hasNext()) last=target.poll();
        return last;
    }
}
