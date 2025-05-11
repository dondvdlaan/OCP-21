package dev.manyroads.stacks.bracketsarebalanced;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Deque<Character> brackets = new ArrayDeque<>();
        Map<Character, Character> pairs = Map.of('}', '{', ']', '[', ')', '(');
        boolean isBalanced = true;
        Scanner scanner = new Scanner(System.in);
        char[] chars = scanner.next().toCharArray();
        for (char aChar : chars) {
            System.out.println(aChar);
            switch (aChar) {
                case '{', '(', '[' -> brackets.addLast(aChar);
                case '}', ')', ']' -> {
                    if (!brackets.isEmpty() &&
                            brackets.peekLast().equals(pairs.get(aChar))) brackets.pollLast();
                    else {
                        isBalanced = false;
                        break;
                    }
                }
            }
        }
        System.out.println(brackets.isEmpty() && isBalanced);
        scanner.close();
    }
}
