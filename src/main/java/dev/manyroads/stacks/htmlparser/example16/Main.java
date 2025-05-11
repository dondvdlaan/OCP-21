package dev.manyroads.stacks.htmlparser.example16;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    static final char TAG_FIRST_CHAR = '<';
    static final char TAG_LAST_CHAR = '>';
    static final char CLOSING_TAG_IDENTIFIER = '/';

    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();

        Deque<Integer> startingContents = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == TAG_FIRST_CHAR) {
                // tag starts
                if (input.charAt(i + 1) == CLOSING_TAG_IDENTIFIER) {
                    // it is a closing tag so children have to be printed
                    System.out.println(input.substring(startingContents.pollFirst(), i));
                    // determine end of closing tag
                    i = getIndexOfNextChar(input, i, TAG_LAST_CHAR);
                } else {
                    // it is an opening tag, determine end of it
                    i = getIndexOfNextChar(input, i, TAG_LAST_CHAR);
                    // also push the next char to the starting contents
                    startingContents.push(i + 1);
                }
            } else {
                // no tag -> this is content, determine end of it
                i = getIndexOfNextChar(input, i, TAG_FIRST_CHAR);
                // poll current starting content and print it
                System.out.println(input.substring(startingContents.pollFirst(), i));
                // determine end of this contents closing tag
                i = getIndexOfNextChar(input, i, TAG_LAST_CHAR);
            }
        }
    }

    private static int getIndexOfNextChar(String str, int startingIndex, char c) {
        int k = 0;
        while (str.charAt(startingIndex + k) != c) {
            k++;
        }
        return startingIndex + k;
    }
}