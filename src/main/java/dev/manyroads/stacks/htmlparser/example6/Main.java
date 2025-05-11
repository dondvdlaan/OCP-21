package dev.manyroads.stacks.htmlparser.example6;

import java.util.Scanner;
import java.util.regex.*;

class HtmlParser {
    private final Pattern pattern;
    HtmlParser(String regex) {
        pattern = Pattern.compile(regex);
    }
    public void parse(CharSequence html) {
        Matcher tagContentMatcher = pattern.matcher(html);
        while (tagContentMatcher.find()) {
            String content = tagContentMatcher.group("CONTENT");
            parse(content);
            System.out.println(content);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HtmlParser parser = new HtmlParser("<(\\w+)>(?<CONTENT>.*?)</\\1>");
        parser.parse(sc.nextLine());
    }
}
