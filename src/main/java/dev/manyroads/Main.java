package dev.manyroads;


import java.util.Queue;
import java.util.Scanner;
import java.lang.String;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) {
        class ConcurrentQueueTest {
            public static void main(String[] args) throws InterruptedException {
                Queue<Integer> numbers = new ConcurrentLinkedQueue<>();
                addNumbers(numbers);

                Thread thread1 = new Thread(() -> pollNumbers(numbers));
                Thread thread2 = new Thread(() -> pollNumbers(numbers));

                thread1.start();
                thread2.start();

                thread1.join();
                thread2.join();

                System.out.println(numbers.size()); // ?
            }

            private static void addNumbers(Queue<Integer> target) {
                for (int i = 0; i < 100_000; i++) {
                    target.add(i);
                }
            }

            private static void pollNumbers(Queue<Integer> target) {
                for (int i = 0; i < 50_000; i++) {
                    target.poll();
                }
            }
        }
    }
}

