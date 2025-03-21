package dev.manyroads.projects.gametictactoe.stage3;

import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

/**
 * In this stage, we’re going to analyze the game state to determine if either player has already won the game or
 * it is still ongoing, if the game is a draw, or if the user has entered an impossible game state
 * (two winners, or with one player having made too many moves).
 * <p>
 * Objectives
 * In this stage, your program should:
 * Take a string entered by the user and print the game grid as in the previous stage.
 * Analyze the game state and print the result. Possible states:
 *
 * Game not finished when neither side has three in a row but the grid still has empty cells.
 * Draw when no side has a three in a row and the grid has no empty cells.
 * X wins when the grid has three X’s in a row (including diagonals).
 * O wins when the grid has three O’s in a row (including diagonals).
 * Impossible when the grid has three X’s in a row as well as three O’s in a row,
 * or there are a lot more X's than O's or vice versa (the difference should be 1 or 0;
 * if the difference is 2 or more, then the game state is impossible).
 * <p>
 * In this stage, we will assume that either X or O can start the game.
 * Example
 * > XXXOO__O_
 * ---------
 * | X X X |
 * | O O _ |
 * | _ O _ |
 * ---------
 * X wins
 */
public class MainTictactoe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String board = scanner.nextLine();
        System.out.println(Board.getInstance().readBoard(board).determineState().toString());
        scanner.close();
    }
}

class Board {
    private static Board instance;
    private final char[][] fields;
    private StateEnum state;

    private Board() {
        this.fields = new char[3][3];
        this.state = StateEnum.UNINITIALIZED;
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
        sb.append(state.getStateDesc());
        return sb.toString();
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
