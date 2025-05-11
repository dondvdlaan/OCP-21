package dev.manyroads.stacks.htmlparser.example17;

import java.util.*;

class Main {
    static Map<String, Integer> tags = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String html = scanner.nextLine();

        parseHtml(html);
    }

    private static void parseHtml(String html) {
        char[] str = html.toCharArray();
        int tagNameCount = 1;
        StringBuilder content = new StringBuilder();
        boolean insideContent = false;

        for (int i = 0; i < str.length; i++) {
            if (str[i] == '<' && str[i + 1] != '/' && !insideContent) {
                String tagName = getTagName(str, i + 1);
                if (tags.containsKey(tagName)) {
                    tagNameCount = tags.get(tagName) + 1;
                }
                tags.put(tagName, tagNameCount);
                i = i + tagName.length() + 1;
                insideContent = true;
            } else if (str[i] == '<' && str[i + 1] == '/') {
                String tagName = getTagName(str, i + 2);
                if (tags.containsKey(tagName) && tags.get(tagName) == tagNameCount) {
                    tags.put(tagName, tagNameCount - 1);
                    if (content.indexOf("<") >= 0) {
                        parseHtml(content.toString());
                    }
                    System.out.println(content);
                    if (html.indexOf('<', i + tagName.length() + 3) >= 0) {
                        parseHtml(html.substring(i + tagName.length() + 3));
                    }
                    return;
                }
                content.append(str[i]);
            } else {
                content.append(str[i]);
            }
        }
    }

    private static String getTagName(char[] str, int start) {
        StringBuilder tagName = new StringBuilder();
        for (int i = start; i < str.length; i++) {
            if (str[i] == '>') {
                break;
            }
            tagName.append(str[i]);
        }
        return tagName.toString();
    }
}
