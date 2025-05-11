package dev.manyroads.stacks.htmlparser.example1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * "<([a-z\d]+)>" - matches a start tag. Tag names could contain one or more characters in the range a-z or digits.
 * "(.+)" - matches content between tags.
 * "<\/\1>" -   matches an end tag with the same name as the start tag.
 *              \1 matches the same text as most recently matched by the 1st capturing group.
 */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printTagValue(scanner.nextLine());
    }

    public static void printTagValue(String html) {
        Pattern p = Pattern.compile("<([a-z\\d]+)>(.+)</\\1>");
        Matcher m = p.matcher(html);
        while (m.find()) {
            printTagValue(m.group(2));
            System.out.println(m.group(2));
        }
    }
}
