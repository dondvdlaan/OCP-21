package dev.manyroads.stacks.htmlparser.example9;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        Stack<String> stackStr = new Stack<>();
        Stack<Integer> stackInt = new Stack<>();
        stackStr.push("");
        Matcher matcher = Pattern.compile("<[^>]*>").matcher(text);
        while (matcher.find()) {
            String match = matcher.group().replace("/", "");
            if (stackStr.peek().equals(match)) {
                stackStr.pop();
                System.out.println(text.substring(stackInt.pop(), matcher.start()));
            } else {
                stackStr.push(match);
                stackInt.push(matcher.end());
            }
        }
    }
}

