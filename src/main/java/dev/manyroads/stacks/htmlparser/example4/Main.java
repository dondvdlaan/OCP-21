package dev.manyroads.stacks.htmlparser.example4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * If the matched subsequence is not a closing tag, its termination index gets pushed onto the stack.
 * Otherwise, it appends the substring from the above index (taken from the stack) to the beginning of
 * the currently matched subsequence.
 */
class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pattern pattern = Pattern.compile("<[^>]+>");
        String text = scanner.nextLine();
        Matcher matcher = pattern.matcher(text);

        Deque<Integer> stack = new ArrayDeque<>();

        while (matcher.find()) {
            if (matcher.group().startsWith("</")) {
                System.out.append(text, stack.pop(), matcher.start());
                System.out.println();
            } else {
                stack.push(matcher.end());
            }
        }
    }
}
