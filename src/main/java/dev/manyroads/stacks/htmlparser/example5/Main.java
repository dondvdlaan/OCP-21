package dev.manyroads.stacks.htmlparser.example5;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        final Deque<MatchResult> dq = new LinkedList<>();

        final var s = scanner.nextLine();

        final var openingTagsPattern = Pattern.compile("<[a-zA-Z0-9]+>");
        final var tagsPattern = Pattern.compile("</?[a-zA-Z0-9]+>");

        tagsPattern.matcher(s).results().forEach(matchResult -> {
            if (openingTagsPattern.matcher(matchResult.group()).matches()) {
                dq.offerFirst(matchResult);
            } else {
                System.out.println(s.substring(dq.pollFirst().end(), matchResult.start()));
            }
        });
    }
}

