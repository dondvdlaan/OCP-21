package dev.manyroads.stacks.htmlparser.example15;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[] inputChars = input.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();
        boolean tagStarted = false;
        for (int i = 0; i < inputChars.length; i++) {
            if (inputChars[i] == '<') {
                tagStarted = true;
            }
            if (inputChars[i] == '>' && tagStarted && i + 1 < inputChars.length) {
                tagStarted = false;
                stack.push(i + 1);
            }
            if (inputChars[i] == '/' && tagStarted && !stack.isEmpty()) {
                tagStarted = false;
                System.out.println(input.substring(stack.pop(), i - 1));
            }
        }
    }
}
