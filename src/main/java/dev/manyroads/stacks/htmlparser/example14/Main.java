package dev.manyroads.stacks.htmlparser.example14;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Deque<String> children = new ArrayDeque<>();
        List<String> siblings = new ArrayList<>();
        Deque<String> content = new ArrayDeque<>();
        Pattern pattern = Pattern.compile("<(\\w+)>(.*?)</\\1>");
        Matcher matcher = pattern.matcher(input);
        children.push(input);
        while (!(children.isEmpty() && siblings.isEmpty())) {
            while (matcher.find()) {
                if (!matcher.group().equals(input)) {
                    siblings.add(matcher.group());

                } else {
                    children.push(matcher.group(2));
                }
            }
            if (!siblings.isEmpty()) {
                input = siblings.remove(0);
                matcher.reset(input);
                continue;
            }
            if (!children.isEmpty()) {
                input = children.pop();
                content.push(input);
                matcher.reset(input);
            }
        }
        content.stream().skip(1).forEach(System.out::println);
    }
}

