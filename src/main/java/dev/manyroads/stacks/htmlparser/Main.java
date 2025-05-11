package dev.manyroads.stacks.htmlparser;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * The solution I have is based on the use of queues and stacks as this is the objective of the task.
 * As some have pointed out, there are other ways to solve this problem using recursion or regex.
 * We can solve this by maintaining two stacks, one a stack of string for keeping track of "open tags", another a stack of queue to keep track of content.
 * The idea is that the stack of tags, keeps track of the tag being processed and the content stack keeps track of all the content corresponding to the tag being processed.
 * The input string is parsed into a list of tokens where each token represents a open tag or text or close tag.
 *
 * Steps of execution for an input string:
 * <html><input><h2><a>hello</a><br>world</br></h2></input></html>
 * - Step 1: <html> tag
 * Tag Stack        Content Stack
 * <html>            <Empty Queue>
 *
 * - Step 2: <input> tag
 * Tag Stack         Content Stack
 * <input>          <Empty Queue>
 * <html>           <Empty Queue>
 *
 * - Step 3: <h2> tag
 * Tag Stack           Content Stack
 * <h2>                 <Empty Queue>
 * <input>            <Empty Queue>
 * <html>             <Empty Queue>
 *
 * - Step 4: <a> tag
 * Tag Stack             Content Stack
 * <a>                      <Empty Queue>
 * <h2>                    <Empty Queue>
 * <input>               <Empty Queue>
 * <html>                <Empty Queue>
 *
 * - Step 5: hello text
 * Tag Stack            Content Stack
 * <a>                      hello
 * <h2>                   <Empty Queue>
 * <input>               <Empty Queue>
 * <html>                <Empty Queue>
 *
 * - Step 6: </a> close tag
 *   print: "hello"
 * Tag Stack              Content Stack
 * <h2>                    <a>hello<a>
 * <input>               <Empty Queue>
 * <html>                <Empty Queue>
 *
 * - Step 7: <br> tag
 * Tag Stack          Content Stack
 * <br>                <Empty Queue>
 * <h2>                <a>hello<a>
 * <input>           <Empty Queue>
 * <html>            <Empty Queue>
 *
 * - Step 8: world text
 * Tag Stack           Content Stack
 * <br>                  world
 * <h2>                 <a>hello<a>
 * <input>             <Empty Queue>
 * <html>              <Empty Queue>
 *
 * - Step 9: </br> close tag
 *   print "world"
 * Tag Stack            Content Stack
 * <h2>                  <a>hello<a><br>world</br>
 * <input>             <Empty Queue>
 * <html>              <Empty Queue>
 *
 * - Step 10:  </h2> close tag
 *   print: "<a>hello<a><br>world</br>"
 * Tag Stack         Content Stack
 * <input>            <h2><a>hello<a><br>world</br></h2>
 * <html>             <Empty Queue>
 *
 * - Step 11: </input> close tag
 *   print "<h2><a>hello<a><br>world</br></h2>"
 * Tag Stack           Content Stack
 * <html>              <input><h2><a>hello<a><br>world</br></h2></input>
 *
 * - Step 11: </html> close tag
 *   print: "<input><h2><a>hello<a><br>world</br></h2></input>"
 *
 * The algorithm has the following logic:
 * 1. For open tag:
 * 	- Push new queue into content stack
 * 	- Push open tag into tag stack
 * 2. For text:
 * 	- Add text to queue on top of the content stack
 * 3. For close tag:
 * 	- Print content from queue on top of content stack(by popping)
 * 	- Decorate the content with open tag and close tag
 * 	- Add the new text to queue on top of the content stack
 * 	Testing
 * 	<html><h1>content1</h1><div><h2>content2</h2></div></html>
 */
public class Main {
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
