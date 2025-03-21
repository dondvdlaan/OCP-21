package dev.manyroads.projects.gametictactoe.stage4;

import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * First move!
 * Description
 * It's time to make our game interactive! Now we're going to add the ability for a user to make a move. To do this,
 * we need to divide the grid into cells. Suppose the top left cell has the coordinates (1, 1) and
 * the bottom right cell has the coordinates (3, 3):
 * (1, 1) (1, 2) (1, 3)
 * (2, 1) (2, 2) (2, 3)
 * (3, 1) (3, 2) (3, 3)
 * The program should ask the user to enter the coordinates of the cell where they want to make a move.
 * In this stage, the user plays as X, not O. Keep in mind that the first coordinate goes from top to bottom
 * and the second coordinate goes from left to right. The coordinates can include the numbers 1, 2, or 3.
 * <p>
 * What happens if the user enters incorrect coordinates? The user could enter symbols instead of numbers,
 * or enter coordinates representing occupied cells or cells that aren't even on the grid. You need to
 * check the user's input and catch possible exceptions.
 * <p>
 * Objectives
 * The program should work as follows:
 * Get the initial 3x3 grid from the input as in the previous stages. Here the user should input 9
 * symbols representing the field, for example, _XXOO_OX_.
 * Output this 3x3 grid as in the previous stages.
 * Prompt the user to make a move. The user should input 2 coordinate numbers that represent the cell
 * where they want to place their X, for example, 1 1.
 * Analyze user input. If the input is incorrect, inform the user why their input is wrong:
 * Print This cell is occupied! Choose another one! if the cell is not empty.
 * Print You should enter numbers! if the user enters non-numeric symbols in the coordinates input.
 * Print Coordinates should be from 1 to 3! if the user enters coordinates outside the game grid.
 * Keep prompting the user to enter the coordinates until the user input is valid.
 * If the input is correct, update the grid to include the user's move and print the updated grid to the console.
 * To summarize, you need to output the field 2 times: once before the user's move (based on the first line
 * of input) and once after the user has entered valid coordinates (then you need to update the grid to
 * include that move).
 * > X_X_O____
 * ---------
 * | X   X |
 * |   O   |
 * |       |
 * ---------
 * > 3 1
 * ---------
 * | X   X |
 * |   O   |
 * | X     |
 * ---------
 */
public class MainTictactoe {

    public static void main(String[] args) {
        Board.getInstance().readUserInput();
    }
}

class Board {
    private static Board instance;
    private final char[][] fields;
    private StateEnum state;
    private Scanner scanner;

    private Board() {
        this.fields = new char[3][3];
        this.state = StateEnum.UNINITIALIZED;
        this.scanner = new Scanner(System.in);
    }

    static Board getInstance() {
        if (instance == null) return new Board();
        return instance;
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
        // System.out.print(UserInput.BOARD.getUserMessage());
        String board = scanner.nextLine();
        System.out.println(readBoard(board).toString());
        boolean isInputOK = false;
        while (!isInputOK) {
            //System.out.print(UserInput.INPUT_NRS.getUserMessage());
            String[] sNumbers = scanner.nextLine().split("\\s+");
            int row = 0, col = 0;
            if (sNumbers.length == 2) {
                if (isDigit(sNumbers[0]) && isDigit(sNumbers[1])) {
                    row = Integer.parseInt(sNumbers[0]);
                    col = Integer.parseInt(sNumbers[1]);

                    if ((row > 0 && row < 4) && (col > 0 && col < 4)) {
                        if (checkMove(row, col)) {
                            updateBoard(row, col, 'X');
                            System.out.println(this);
                            isInputOK = true;
                        } else System.out.println(UserInput.CELL_OCCUPIED.getUserMessage());
                    } else System.out.println(UserInput.NUMBERS_RANGE.getUserMessage());
                } else System.out.println(UserInput.NUMBERS_ONLY.getUserMessage());
            }
        }
        scanner.close();
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
                state = StateEnum.DRAW;
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
        // sb.append(state.getStateDesc());
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
