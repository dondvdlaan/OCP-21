package dev.manyroads.projects.gametictactoe.stage5;

import java.util.Scanner;
import java.util.stream.Collectors;

import static dev.manyroads.projects.gametictactoe.stage5.StateEnum.DRAW;
import static dev.manyroads.projects.gametictactoe.stage5.StateEnum.O_WINS;
import static dev.manyroads.projects.gametictactoe.stage5.StateEnum.X_WINS;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * Our game is almost ready! Now let's combine what we’ve learned in the previous stages to make a game of tic-tac-toe
 * that two players can play from the beginning (with an empty grid) through to the end (until there is a draw, or one of the players wins).
 * <p>
 * The first player has to play as X and their opponent plays as O.
 * <p>
 * Objectives
 * In this stage, you should write a program that:
 * - Prints an empty grid at the beginning of the game.
 * - reates a game loop where the program asks the user to enter the cell coordinates,
 * analyzes the move for correctness and shows a grid with the changes if everything is okay.
 * - Ends the game when someone wins or there is a draw.
 * - You need to output the final result at the end of the game. Good luck!
 * Example 1.
 * ---------
 * |       |
 * |       |
 * |       |
 * ---------
 * > 2 2
 */
public class MainTictactoe {

    public static void main(String[] args) {
        Board.getInstance().play();
    }
}

class Board {
    private static Board instance;
    private final char[][] fields;
    private StateEnum state;
    private final Scanner scanner;
    private char userTurn;

    private Board() {
        this.fields = new char[3][3];
        this.state = StateEnum.UNINITIALIZED;
        this.scanner = new Scanner(System.in);
        this.userTurn = 'X';
    }

    static Board getInstance() {
        if (instance == null) return new Board();
        return instance;
    }

    void play() {
        String initState = "_________";
        System.out.println(readBoard(initState).toString());
        while (state != DRAW && state != X_WINS && state != O_WINS) {
            readUserInput();
            switchUser();
            determineState();
        }
        System.out.println(state.getStateDesc());
        scanner.close();
        }

    Board readBoard(String board) {
        int rowNr = 0;
        for (char[] row : fields) {
            for (int i = 0; i < row.length; i++) {
                row[i] = board.charAt(rowNr * fields.length + i);
            }
            rowNr++;
        }
        return this;
    }

    Board readUserInput() {
        boolean isInputOK = false;
        while (!isInputOK) {
            System.out.print("> ");
            String[] sNumbers = scanner.nextLine().split("\\s+");
            int row = 0, col = 0;
            if (sNumbers.length == 2) {
                if (isDigit(sNumbers[0]) && isDigit(sNumbers[1])) {
                    row = Integer.parseInt(sNumbers[0]);
                    col = Integer.parseInt(sNumbers[1]);

                    if ((row > 0 && row < 4) && (col > 0 && col < 4)) {
                        if (checkMove(row, col)) {
                            updateBoard(row, col, userTurn);
                            System.out.println(this);
                            isInputOK = true;
                        } else System.out.println(UserInput.CELL_OCCUPIED.getUserMessage());
                    } else System.out.println(UserInput.NUMBERS_RANGE.getUserMessage());
                } else System.out.println(UserInput.NUMBERS_ONLY.getUserMessage());
            }
        }
        return this;
    }

    Board determineState() {
        int totalX = 0;
        int totalO = 0;
        int rowX = 0;
        int rowO = 0;
        int colX = 0;
        int colO = 0;
        int dig1X = 0;
        int dig1O = 0;
        int dig2X = 0;
        int dig2O = 0;
        boolean isXWinner = false;
        boolean isOWinner = false;
        for (int j = 0; j < fields.length; j++) {
            // check diagonal for winners
            if (fields[j][j] == 'X') dig1X++;
            if (fields[j][j] == 'O') dig1O++;
            if (fields[j][fields.length - 1 - j] == 'X') dig2X++;
            if (fields[j][fields.length - 1 - j] == 'O') dig2O++;
            // check row/col and count fields
            for (int i = 0; i < fields[j].length; i++) {
                switch (fields[j][i]) {
                    case 'X' -> {
                        totalX++;
                        rowX++;
                    }
                    case 'O' -> {
                        totalO++;
                        rowO++;
                    }
                    case '_' -> {
                    }
                    default -> {
                        System.out.println("No such field");
                    }
                }
                // check column for winners
                if (fields[i][j] == 'X') colX++;
                if (fields[i][j] == 'O') colO++;
            }
            // remember winner
            isXWinner = isXWinner || (rowX == 3 || colX == 3 || dig1X == 3 || dig2X == 3);
            isOWinner = isOWinner || (rowO == 3 || colO == 3 || dig1O == 3 || dig2O == 3);

            rowO = 0;
            rowX = 0;
            colX = 0;
            colO = 0;
        }

        // set state
        if (isOWinner && isXWinner || abs(totalX - totalO) >= 2) {
            state = StateEnum.IMPOSSIBLE;
            return this;
        }
        if (!isXWinner && !isOWinner) {
            if (totalX + totalO != pow(fields.length, 2)) {
                state = StateEnum.NOT_FINISHED;
                return this;
            } else {
                state = DRAW;
                return this;
            }
        }
        if (isXWinner) state = StateEnum.X_WINS;
        if (isOWinner) state = StateEnum.O_WINS;
        return this;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("---------\n");
        for (char[] row : fields) {
            sb.append("| " + new String(row).chars().mapToObj(i -> (char) i + " ").collect(Collectors.joining()) + "|\n");
        }
        sb.append("---------\n");
        return sb.toString();
    }

    // submethods
    boolean checkMove(int row, int col) {
        return fields[row - 1][col - 1] == '_';
    }

    boolean isDigit(String sNumber) {
        char c = sNumber.charAt(0);
        return c >= '0' && c <= '9';
    }

    void updateBoard(int row, int col, char symbol) {
        this.fields[row - 1][col - 1] = symbol;
    }

    void switchUser() {
        this.userTurn = userTurn == 'X' ? 'O' : 'X';
    }
}

enum StateEnum {
    NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMPOSSIBLE("Impossible"),
    UNINITIALIZED("State uninitialized");

    private String stateDesc;

    StateEnum(String stateDesc) {
        this.stateDesc = stateDesc;
    }

    public String getStateDesc() {
        return stateDesc;
    }
}

enum UserInput {
    BOARD("Input board coordinates: "),
    INPUT_NRS("Input 2 coordinate numbers between 1 and 3: "),
    CELL_OCCUPIED("This cell is occupied! Choose another one!"),
    NUMBERS_ONLY("You should enter numbers!"),
    NUMBERS_RANGE("Coordinates should be from 1 to 3!");

    private String userMessage;

    UserInput(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
