package dev.manyroads.stacks.htmlparser;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main2 {
    static StringBuilder sbText = new StringBuilder();
    static StringBuilder sbTag = new StringBuilder();
    static Deque<String> stackTags = new ArrayDeque<>();
    static Deque<String> stackText = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] sArray = scanner.next().split("");

        boolean startRecTag = false;
        boolean startRecText = false;

        for (int i = 0; i < sArray.length; i++) {

//            System.out.println("stackTags:" + stackTags);
//            System.out.println("stackText:" + stackText);

            // closing tag
            if (sArray[i].equals("/")) {
                if (stackTags.peekLast().contains("html")) {
                    //System.out.println("In stackTags.peekLast().contains(\"html\") ");
                    System.out.println(stackText.peekLast());
                }
                else processClosingTag();
            }

            // Start recording tag
            if (i < sArray.length - 1 && !sArray[i + 1].equals("/") && sArray[i].equals("<"))
                startRecTag = true;

            // Recording Tag
            if (startRecTag)
                sbTag.append(sArray[i]);

            // Stop recording tag, store tag and default text
            if (startRecTag && sArray[i].equals(">")) {
                startRecTag = false;
                //System.out.println("Adding to stackTags: " + sbTag);
                stackTags.add(sbTag.toString());
                stackText.add("<NO TEXT>");
                sbTag = new StringBuilder();

                // ...and start recording text
                if (i < sArray.length - 1 && !sArray[i + 1].equals("<"))
                    startRecText = true;
            }

            // Recording text
            if (startRecText)
                sbText.append(sArray[i]);

            // Stop recording text and store
            if (startRecText && sArray[i].equals("<")) {
                startRecText = false;
                // trim stackText from '>' and '<' chars and add to stack
                stackText.pollLast();
                stackText.add(sbText.substring(1, sbText.toString().length() - 1));
                sbText = new StringBuilder();
            }
        }
    }

    static void processClosingTag() {
        String textToPrint = stackText.peekLast();
        System.out.println(stackText.peekLast());
        String closingTag = stackTags.peekLast().replace("<", "</");
        var tagTextClosingTag = new StringBuilder().append(stackTags.peekLast()).append(textToPrint).append(closingTag);

        // Replace last text entree with tagTextClosingTag
        stackText.pollLast();
        stackTags.pollLast();
        if (stackText.peekLast() != null) {
            if (!stackText.peekLast().equals("<NO TEXT>")) {
                String currentText = stackText.peekLast();
                tagTextClosingTag = new StringBuilder().append(currentText).append(tagTextClosingTag);
            }
            stackText.pollLast();
            stackText.addLast(tagTextClosingTag.toString());
        }
    }
}
