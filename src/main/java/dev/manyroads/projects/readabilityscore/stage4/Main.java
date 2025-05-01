package dev.manyroads.projects.readabilityscore.stage4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

import static java.lang.Math.max;

/**
 * In this stage, you should implement various other scientific approaches to calculate a readability score.
 * <p>
 * Take a look at different ages and corresponding scores in the table in ARI article on Wikipedia. This table is suitable for all the
 * algorithms described in this stage. To calculate the age, use the upper bound of the range. For example, if the range is
 * 12-13-year-olds then it's upper bound is 13-year-olds. https://en.wikipedia.org/wiki/Automated_readability_index
 * <p>
 * The first algorithm is Flesch–Kincaid readability tests. First, you need to create a method that calculates the number of
 * syllables in a word. The formula is given below. You can find more information in the corresponding article on Wikipedia.
 * You can use the second formula to calculate the index; it allows you to easily calculate the age of a person using the
 * same table from the Automated Readability Index. https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests
 * <p>
 * The second one is SMOG index. It stands for Simple Measure of Gobbledygook. To calculate it, you need to count the number
 * of polysyllables, which is the number of words with more than 2 syllables. The formula is shown below. You can find out
 * more in the Wikipedia article on SMOG. The article says that at least 30 sentences are required for this index to work
 * properly. Don't pay attention to this, just keep it in mind when you use this index in real life. As in the previous
 * example, the grade level is calculated here, so to get the age of a person you need to use the table from the first link.
 * https://en.wikipedia.org/wiki/SMOG
 * <p>
 * The next one is Coleman–Liau index. The formula is given below. For more information, read the article on Wikipedia.
 * L is the average number  * of characters per 100 words and S is the average number of sentences per 100 words. Like
 * all other indices, the output is a person's grade
 * level. Like all other indices, the result is a minimum grade level required to understand this text.
 * <p>
 * So, in this stage, you should program all three approaches. Don't forget about the Automated readability index! Also,
 * there should be an option to choose all methods at the same time.
 * <p>
 * To count the number of syllables you should use letters a, e, i, o, u, y as vowels. In the short article on Vowels on
 * Wikipedia you can see examples and intricacies with determining vowels in a word with 100% accuracy. So, let's
 * use the following 4 rules: https://simple.wikipedia.org/wiki/Vowel
 * <p>
 * 1. Count the number of vowels in the word.
 * 2. Do not count double-vowels (for example, "rain" has 2 vowels but only 1 syllable).
 * 3. If the last letter in the word is 'e' do not count it as a vowel (for example, "side" has 1 syllable).
 * 4. If at the end it turns out that the word contains 0 vowels, then consider this word as a 1-syllable one.
 * Example
 * > java Main in.txt
 * The text is:
 * This is the front page of the Simple English Wikipedia. Wikipedias are places where people work together to write encyclopedias in different languages. We use Simple English words and grammar here. The Simple English Wikipedia is for everyone! That includes children and adults who are learning English. There are 142,262 articles on the Simple English Wikipedia. All of the pages are free to use. They have all been published under both the Creative Commons License and the GNU Free Documentation License. You can help here! You may change these pages and make new pages. Read the help pages and other good pages to learn how to write pages here. If you need help, you may ask questions at Simple talk. Use Basic English vocabulary and shorter sentences. This allows people to understand normally complex terms or phrases.
 * <p>
 * Words: 137
 * Sentences: 14
 * Characters: 687
 * Syllables: 210
 * Polysyllables: 17
 * Enter the score you want to calculate (ARI, FK, SMOG, CL, all): all
 * <p>
 * Automated Readability Index: 7.08 (about 13-year-olds).
 * Flesch–Kincaid readability tests: 6.31 (about 12-year-olds).
 * Simple Measure of Gobbledygook: 9.42 (about 15-year-olds).
 * Coleman–Liau index: 10.66 (about 17-year-olds).
 * <p>
 * This text should be understood in average by 14.25-year-olds.
 */
public class Main {
    public static void main(String[] args) {
        new ReadabilityScore().run(args[0]);
    }
}

class ReadabilityScore {
    UI ui;
    static int words;
    static int sentences;
    static int chrs;
    static int syllables;
    static int polysyllables;
    double averageAge;

    public ReadabilityScore() {
        ui = UI.getInstance();
    }

    public void run(String fileName) {
        generateParameters(fileName);
        ui.printParameters(words, sentences, chrs, syllables, polysyllables);
        String index = ui.mainMenu();
        averageAge = switch (index) {
            case "ARI" -> new ARI().getReadabilityScore();
            case "FK" -> new FleschKincaid().getReadabilityScore();
            case "SMOG" -> new Smog().getReadabilityScore();
            case "CL" -> new ColemanLiau().getReadabilityScore();
            case "all" -> {
                double age = new ARI().getReadabilityScore();
                age += new FleschKincaid().getReadabilityScore();
                age += new Smog().getReadabilityScore();
                age += new ColemanLiau().getReadabilityScore();
                yield age / 4;
            }
            default -> {
                System.out.println("No such index available");
                yield 0D;
            }
        };
        ui.averageAged(averageAge);
    }

    public double getReadabilityScore() {
        return 0D;
    }

    public static void generateParameters(String fileName) {
        String text = UI.readFile(Path.of(fileName));
        System.out.println(text);
        String rexEOL = "[!?.]";
        String whiteSpace = "\\s+";
        String[] wordsArray = Arrays.stream(text.trim().split(whiteSpace)).toArray(String[]::new);
        words = wordsArray.length;
        sentences = text.trim().split(rexEOL).length;
        chrs = Arrays.stream(wordsArray).map(String::length).reduce(Integer::sum).orElse(0);
        syllables = Arrays.stream(wordsArray).map(ReadabilityScore::countSyllables).reduce(Integer::sum).orElse(0);
        polysyllables = (int) Arrays.stream(wordsArray).filter(w -> getSyllables(w) > 2).count();
    }

    public static int countSyllables(final String word) {
        return max(1, getSyllables(word));
    }

    static int getSyllables(String word) {
        return word.toLowerCase()
                //in words that end with "e" replace
                //"e" with ""
                .replaceAll("e$", "") //e.g base=bas
                //when two vowels appear together,
                //replace them with "a"
                .replaceAll("[aeiouy]{2}", "a") //e.g you == au,
                //beautiful==bautiful
                //again, when two vowels appear together,
                //replace them with "a"
                .replaceAll("[aeiouy]{2}", "a") //e.g au == a,
                //bautiful==batiful
                //replace any character that isn't aeiouy with ""
                .replaceAll("[^aeiouy]", "") //e.g, batiful==aiu,
                //a == a
                .length(); //aiu == 3 syllables, a == 1 syllable
    }
}

class ARI extends ReadabilityScore {

    @Override
    public double getReadabilityScore() {
        if (!(words > 0) || !(sentences > 0)) {
            System.out.println("Can not divide by zero");
            return 0D;
        }
        double score = 4.71 * chrs / words + 0.5 * words / sentences - 21.43;
        UI.getInstance().printResultIndex("Automated Readability Index", score);
        return Double.parseDouble(ui.ageUpperBound(score));
    }

}

class FleschKincaid extends ReadabilityScore {

    @Override
    public double getReadabilityScore() {
        if (!(words > 0) || !(sentences > 0)) {
            System.out.println("Can not divide by zero");
            return 0D;
        }
        double score = 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
        UI.getInstance().printResultIndex("Flesch–Kincaid readability tests", score);
        return Double.parseDouble(ui.ageUpperBound(score));
    }
}

class Smog extends ReadabilityScore {

    @Override
    public double getReadabilityScore() {
        if (!(words > 0) || !(sentences > 0)) {
            System.out.println("Can not divide by zero");
            return 0D;
        }
        double score = 1.043 * Math.sqrt(polysyllables * 30D / sentences) + 3.1291;
        UI.getInstance().printResultIndex("Simple Measure of Gobbledygook", score);
        return Double.parseDouble(ui.ageUpperBound(score));
    }
}

class ColemanLiau extends ReadabilityScore {

    @Override
    public double getReadabilityScore() {
        if (!(words > 0) || !(sentences > 0)) {
            System.out.println("Can not divide by zero");
            return 0D;
        }
        double L = chrs * 100D / words, S = sentences * 100D / words;
        double score = 0.0588 * L - 0.296 * S - 15.8;
        UI.getInstance().printResultIndex("Coleman–Liau index", score);
        return Double.parseDouble(ui.ageUpperBound(score));
    }
}

class UI {
    Supplier<String> userInput = () -> Optional.ofNullable(new Scanner(System.in).nextLine()).orElse("");
    Repository repo;

    private UI() {
        this.repo = new Repository();
    }

    public static UI getInstance() {
        return new UI();
    }

    String readLine() {
        return userInput.get();
    }

    static String readFile(Path pathToFile) {
        try {
            return new String(Files.readAllBytes(pathToFile));
        } catch (SecurityException sec) {
            System.out.println("Security issue with file: " + sec.getMessage());
        } catch (IOException io) {
            System.out.println("Reading issue with file: " + io.getMessage());
        }
        return "";
    }

    public void printParameters(int words, int sentences, int chrs, int syllables, int polysyllables) {
        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + chrs);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
    }

    public String mainMenu() {
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        return readLine();
    }

    public void printResultIndex(String index, double score) {
        System.out.printf("%s: %.2f (about %s-year-olds).\n", index, score, ageUpperBound(score));
    }

    public String ageUpperBound(double score) {
        if(score >14) return "22";
        return repo.getTable().get((long) Math.ceil(score)).getFirst().replaceAll("[0-9]{1,}-", "");
    }

    public void averageAged(double averageAge) {
        System.out.printf("This text should be understood in average by %.2f-year-olds.", averageAge);
    }
}

class Repository {
    private Map<Long, List<String>> tableAgeBrackets;

    public Repository() {
        this.tableAgeBrackets = getTableAgeBrackets();
    }

    public Map<Long, List<String>> getTable() {
        return new HashMap<Long, List<String>>(tableAgeBrackets);
    }

    private Map<Long, List<String>> getTableAgeBrackets() {
        Map<Long, List<String>> tableAgeBrackets = new HashMap<>();
        tableAgeBrackets.put(1L, List.of("5-6", "Kindergarten"));
        tableAgeBrackets.put(2L, List.of("6-7", "First Grade"));
        tableAgeBrackets.put(3L, List.of("7-8", "Second Grade"));
        tableAgeBrackets.put(4L, List.of("8-9", "Third Grade"));
        tableAgeBrackets.put(5L, List.of("9-10", "Fourth Grade"));
        tableAgeBrackets.put(6L, List.of("10-11", "Fifth Grade"));
        tableAgeBrackets.put(7L, List.of("11-12", "Sixth Grade"));
        tableAgeBrackets.put(8L, List.of("12-13", "Seventh Grade"));
        tableAgeBrackets.put(9L, List.of("13-14", "Eighth Grade"));
        tableAgeBrackets.put(10L, List.of("14-15", "Ninth Grade"));
        tableAgeBrackets.put(11L, List.of("15-16", "tenth Grade"));
        tableAgeBrackets.put(12L, List.of("16-17", "Eleventh Grade"));
        tableAgeBrackets.put(13L, List.of("17-18", "Twelfth Grade"));
        tableAgeBrackets.put(14L, List.of("18-22", "College student"));
        return tableAgeBrackets;
    }
}
