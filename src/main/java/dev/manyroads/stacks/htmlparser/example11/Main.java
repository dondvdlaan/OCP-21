package dev.manyroads.stacks.htmlparser.example11;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String htmlExp = scanner.next("<(?<tagName>.+?)>.+</\\k<tagName>>");

        process(htmlExp);
    }

    /** Determine the type of html expression, and return a convenient value
     * denoting the same.
     *
     * @param htmlExp The html expression whose type we wish to determine.
     * @return If htmlExp is a tagged expression, return an Optional consisting of the
     * unwrapped inner contents. Else, if htmlExp is simple text, return an empty Optional.
     */
    private static Optional<String> determineExp(String htmlExp) {
        var matcher = Pattern.compile("^<(?<tagName>.+?)>(?<innerContents>.+)</\\k<tagName>>$").matcher(htmlExp);

        return matcher.find() ? Optional.of(matcher.group("innerContents")) : Optional.empty();
    }

    /** Listify the inner contents of a tagged html expression.
     *
     * We make the assumption that, if unwrapping has returned a non-empty optional, the contents
     * consist exclusively of tagged expressions (as opposed to possibly including one or more
     * text nodes.)
     *
     * @param tagExps The raw String consisting of the various tagged expressions.
     * @return A tokenized list of these tagged expressions.
     */
    private static List<String> listifyInnerContents(String tagExps) {
        var matcher = Pattern.compile("<(?<tagName>.+?)>.+</\\k<tagName>>").matcher(tagExps);
        List<String> innerContentsList = new ArrayList<>();

        while (matcher.find()) {
            innerContentsList.add(matcher.group());
        }

        return innerContentsList;
    }

    /** Solve the exercise (print the various levels of the html expression
     * in a certain order.)
     *
     * @param htmlExp The user input, consisting of an html expression.
     */
    private static void process(String htmlExp) {
        var whatAmI = determineExp(htmlExp);

        // 'htmlExp' is a tagged expression, so we now process its children.
        whatAmI.ifPresentOrElse(innerContents -> {
            var tagExps = listifyInnerContents(innerContents);
            for (var tagExp : tagExps) {
                process(tagExp);
            }
            System.out.println(innerContents);
        }, () -> System.out.println(htmlExp));
    }
}
