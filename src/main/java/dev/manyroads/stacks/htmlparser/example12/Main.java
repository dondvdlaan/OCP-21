package dev.manyroads.stacks.htmlparser.example12;

import java.util.*;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        // put your code here
        try (var scanner = new Scanner(System.in)) {
            var line = scanner.nextLine();
            var tag = parseHtml(line);
            printTag(tag);
        }
    }

    public static String printTag(Tag tag) {

        var sb = new StringBuilder();
        sb.append("<").append(tag.name).append(">");

        if (!tag.content.isEmpty()) {
            sb.append(tag.content);
            System.out.println(tag.content);
        } else {
            var sbChildren = new StringBuilder();
            for (var child : tag.children) {
                sbChildren.append(printTag(child));
            }
            sb.append(sbChildren);
            System.out.println(sbChildren);
        }

        sb.append("</").append(tag.name).append(">");
        return sb.toString();
    }

    public static Tag parseHtml(String html) {

        var stack = new ArrayDeque<Tag>();
        var matcher = Pattern.compile("<(\\w+)[^>]*>|</(\\w+)>|([^<]+)").matcher(html);
        var root = new Tag("root");
        stack.push(root);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                var tag = new Tag(matcher.group(1));
                stack.peek().children.add(tag);
                stack.push(tag);
            } else if (matcher.group(2) != null) {
                stack.pop();
            } else {
                stack.peek().content += matcher.group(3);
            }
        }

        return root.children.get(0);

    }

    static class Tag {
        String name;
        String content = "";
        List<Tag> children = new ArrayList<>();

        Tag(String name) {
            this.name = name;
        }
    }
}