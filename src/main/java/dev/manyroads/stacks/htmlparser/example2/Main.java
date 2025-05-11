package dev.manyroads.stacks.htmlparser.example2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<') {
                if (str.charAt(i + 1) != '/') {
                    stack.push(str.indexOf('>', i) + 1);
                } else {
                    int indexOpen = stack.pop();
                    System.out.println(indexOpen);
                    System.out.println(str.substring(indexOpen, i));
                }
            }
        }
    }
}
