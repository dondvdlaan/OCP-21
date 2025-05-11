package dev.manyroads.stacks.htmlparser.example8;

import java.util.*;

public class Main {

    // Method to parse the HTML string and process tags
    public static void parseHTML(String html) {
        Deque<String> stack = new ArrayDeque<>();
        int i = 0;

        while (i < html.length()) {
            if (html.charAt(i) == '<') {
                int closeTagIndex = html.indexOf('>', i);

                // Extract the tag name
                String tag = html.substring(i + 1, closeTagIndex);

                if (!tag.startsWith("/")) { // Opening tag
                    stack.push(tag);
                } else { // Closing tag
                    String openingTag = stack.pop();
                    int startIndex = html.lastIndexOf("<" + openingTag + ">", i);
                    int contentStart = startIndex + openingTag.length() + 2;
                    int contentEnd = i;

                    // Print the content of the current tag
                    String content = html.substring(contentStart, contentEnd).trim();
                    if (!content.isEmpty()) {
                        System.out.println(content);
                    }
                }

                i = closeTagIndex + 1; // Move past the current tag
            } else {
                i++; // Move to the next character
            }
        }
    }

    // Main method to test the parser
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String htmlContent = scanner.nextLine();
        parseHTML(htmlContent);
        scanner.close();
    }
}
