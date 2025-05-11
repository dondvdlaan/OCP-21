package dev.manyroads.stacks.htmlparser.example13;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    static Deque<String> stack = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        stack.add(input);
        while (!stack.isEmpty()) {
            processAnElement(stack.peekLast());
        }
    }

    private static void processAnElement(String s) {
        s = cutOuterTags(s);
        processChildren(s);
        System.out.println(s);
        stack.pollLast();
    }

    private static void processChildren(String s) {
        if (!s.startsWith("<")){
            return;
        }
        while (!s.isEmpty()) {
            String[] arr = s.split("");
            StringBuilder closingTag = new StringBuilder();
            int i = 0;
            while (!">".equals(arr[i])) {
                closingTag.append(arr[i]);
                if (i == 0) {
                    closingTag.append("/");
                }
                i++;
            }
            closingTag.append(arr[i]);
            String child = s.substring(0, s.indexOf(String.valueOf(closingTag)) + closingTag.length());
            s = s.substring(s.indexOf(String.valueOf(closingTag)) + closingTag.length());
            stack.add(child);
            processAnElement(child);
        }
    }

    private static String cutOuterTags(String s) {
        String[] arr = s.split("");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        int i = 0;
        while (!">".equals(arr[i])) {
            sb.append(arr[i]);
            sb2.append(arr[i]);
            if (i == 0) {
                sb2.append("/");
            }
            i++;
        }
        sb.append(arr[i]);
        sb2.append(arr[i]);
        s = s.substring(sb.length());
        s = s.substring(0, s.length() - sb2.length());
        return s;
    }
}

