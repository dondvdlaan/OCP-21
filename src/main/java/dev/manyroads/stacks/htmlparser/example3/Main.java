package dev.manyroads.stacks.htmlparser.example3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String html = scanner.nextLine();
        parse(html);
    }

    static void parse(String html) {
        Deque<Integer> stack = new ArrayDeque<>();
        int start = 0;
        while (start < html.length()) {
            int tagStart = html.indexOf('<', start);
            int tagEnd = html.indexOf('>', tagStart);

            if (tagStart != -1 && tagEnd != -1) {
                String openTag = html.substring(tagStart, tagEnd);
                if (openTag.startsWith("</")) {
                    System.out.println(html.substring(stack.getFirst() + 1, tagStart));
                    stack.pollFirst();
                } else {
                    stack.offerFirst(tagEnd);
                }
            }
            start = tagEnd + 1;
        }
    }

}
