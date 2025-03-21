package dev.manyroads.projects.bullsandcows.stage1;

/**
 * Game log
 * Description
 * Let's start by reminding ourselves how the game goes. There are two players: the first writes a secret 4-digit code using
 * different digits, and the second has to guess it. At each turn, the second player writes a 4-digit number that they think
 * might be the correct answer. Then, the first player grades that answer using bulls and cows as a notation. If a digit in
 * the given answer matches a digit and its position in the code, it's called a "bull." If a given digit appears in the code
 * but its position doesn't match, then it's called a "cow." The first player reveals how many bulls and cows there are.
 * The information is general; in other words, it isn't bound to any particular digit. For example:
 * <p>
 * The code is 4931.
 * The answer is 1234.
 * The grade is 1 bull and 2 cows.
 * <p>
 * Here, 3 is a bull, 1 and 4 are cows. If all the digits are bulls, the secret code has been guessed and the game ends.
 * If not, the game continues and the second player tries again.
 * <p>
 * This may sound rather complicated, but don't worry, we will take it very gradually. In this stage, you will not even
 * have to implement the gameplay: all you need to do now is output the text that could be in the game. In other words,
 * you don't have to worry about handling any requests or processing data: your goal is to display an example text of the game.
 * Objectives
 * In this stage, your program should:
 * Print a predefined game log that contains at least two turns.
 * The output to be graded is a 4-digit code consisting of unique digits.
 * The last line of your output contains a secret number.
 * Your program should not take any input data. You only have to print a static game log similar to the examples below.
 * Example 2
 * The secret code is prepared: ****.
 * <p>
 * Turn 1. Answer:
 * 1234
 * Grade: None.
 * <p>
 * Turn 2. Answer:
 * 9876
 * Grade: 4 bulls.
 * Congrats! The secret code is 9876.
 */
public class Main {

    static void gameLog() {
        final String SECRET_CODE_MSSG = "The secret code is prepared: ****.";
        final String CONGRAT_MSSG = "Congrats! The secret code is %d.";
        final String TURN_MSSG = ("Turn %d. Answer:");
        final String ANSWER_1 = ("1234");
        final String ANSWER_2 = ("9876");
        final String GRADE_NONE = ("Grade: None.");
        final String GRADE_BULLS = ("Grade: %d bulls.");
        int code = 9876;
        int maxTurns = 2;
        int turns = 0;
        System.out.println(SECRET_CODE_MSSG);
        while (turns < maxTurns) {
            turns++;
            System.out.printf((TURN_MSSG) + "%n", turns);
            System.out.println(ANSWER_1);
            System.out.println(GRADE_NONE);
            turns++;
            System.out.println(String.format(TURN_MSSG, turns));
            System.out.println(ANSWER_2);
            System.out.println(String.format(GRADE_BULLS, 4));

            System.out.println(String.format(CONGRAT_MSSG, code));
        }
    }

    public static void main(String[] args) {
        gameLog();
    }
}
