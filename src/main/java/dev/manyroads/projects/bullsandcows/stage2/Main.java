package dev.manyroads.projects.bullsandcows.stage2;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Description
 * Let's add some interactivity to our program. The program should create a 4-digit secret code, and the player should
 * try to guess it on the first try. The program should give a grade to evaluate how accurate the player was.
 * As you remember, a correctly guessed digit is a cow, and if its position is also correct, then it is a bull.
 * After the player tries to guess the secret code, the program should grade the attempt and finish the execution.
 * <p>
 * For example, if the secret code is 9305, then:
 * <p>
 * The number 9305 contains 4 bulls and 0 cows since all 4 digits are correct and their positions are correct as well.
 * It's the only number that can contain 4 bulls, so it's also the secret code itself.
 * The numbers 3059, 3590, 5930, 5039 contain 0 bulls and 4 cows since all 4 digits are correct but their positions
 * don't match the positions in the secret code.
 * The numbers 9306, 9385, 9805, 1305 contain 3 bulls.
 * The numbers 9350, 9035, 5309, 3905 contain 2 bulls and 2 cows.
 * The numbers 1293, 5012, 3512, 5129 contain 0 bulls and 2 cows.
 * The numbers 1246, 7184, 4862, 2718 contain 0 bulls and 0 cows.
 * Note that guesses can contain repetitive digits, so:
 * <p>
 * The number 1111 contains 0 bulls and 0 cows.
 * The number 9999 contains 1 bull and 3 cows.
 * The number 9955 contains 2 bulls and 2 cows.
 * Objectives
 * In this stage, your goal is to write the core part of the game: the grader.
 * <p>
 * Your program should take a 4-digit number as an input.
 * Use a predefined 4-digit code and grade the answer that was input. You can do it digit by digit.
 * The grade is considered correct if it contains number-and-word pairs (like X bulls and Y cows)
 * that give the correct information. If the answer doesn't contain any bulls and cows, you should output None.
 * <p>
 * Examples
 * The greater-than symbol followed by a space > represents the user input. Note that it's not part of the input.
 * Example 1
 * > 1234
 * Grade: 1 cow(s). The secret code is 9305.
 * > 9087
 * Grade: 1 bull(s) and 1 cow(s). The secret code is 9305.
 */
public class Main {

    static int userInput() {
        return new Scanner(System.in).nextInt();
    }

    static int generateCode() {
        Supplier<Integer> generateCode = () -> (int) (Math.random() * 9999);
        return generateCode.get();
    }

    static String grader(int userInput, int code) {
        // check winner
        if (userInput == code) return String.format(CONSTANTS.CONGRAT_MSSG, code);
        String sCode = String.valueOf(code);
        String sUserInput = String.valueOf(userInput);
        //long bulls = IntStream.range(0, sCode.length()).filter(i -> sCode.charAt(i) == sUserInput.charAt(i)).count();
        int cs = 0;
        int bs = 0;
        for (int j = 0; j < sCode.length(); j++) {
            for (int i = 0; i < sUserInput.length(); i++) {
                if (sCode.charAt(j) == sUserInput.charAt(i)) {
                    bs = i == j ? ++bs : bs;
                    cs = i != j ? ++cs : cs;
                }
            }
        }
        if (bs > 0 && cs > 0) return String.format(CONSTANTS.GRADE_BULLS_COWS, bs, cs, code);
        if (bs > 0 && cs == 0) return String.format(CONSTANTS.GRADE_BULLS, bs, code);
        if (bs == 0 && cs > 0) return String.format(CONSTANTS.GRADE_COWS, cs, code);
        return String.format(CONSTANTS.GRADE_NONE, code);
    }

    static void gameLog() {
        int code = generateCode();
        int maxTurns = 1;
        int turns = 0;
        while (turns < maxTurns) {
            turns++;
            System.out.println(code);
            System.out.println(grader(userInput(), code));
        }
    }

    public static void main(String[] args) {
        gameLog();
    }
}

class CONSTANTS {
    final static String SECRET_CODE_MSSG = "The secret code is prepared: ****.";
    final static String CONGRAT_MSSG = "Congrats! The secret code is %d.";
    final static String TURN_MSSG = ("Turn %d. Answer:");
    final static String ANSWER_1 = ("1234");
    final static String ANSWER_2 = ("9876");
    final static String GRADE_NONE = ("Grade: None. The secret code is %d.");
    final static String GRADE_BULLS = "Grade: %d bull(s). The secret code is %d.";
    final static String GRADE_COWS = "Grade: %d cows(s). The secret code is %d.";
    final static String GRADE_BULLS_COWS = "Grade: %d bull(s) and  %d cows(s). The secret code is %d.";
}
