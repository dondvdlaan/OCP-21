package dev.manyroads.projects.gametictactoe.stage2;

import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Our program should be able to display the grid at all stages of the game. Now we’re going to write a program that
 * allows the user to enter a string representing the game state and correctly prints the 3x3 game grid based on this
 * input. We’ll also add some boundaries around the game grid.
 * <p>
 * Objectives
 * Reads a string of 9 symbols from the input and displays them to the user in a 3x3 grid.
 * The grid can contain only X, O and _ symbols.
 * Outputs a line of dashes --------- above and below the grid, adds a pipe | symbol to the beginning
 * and end of each line of the grid, and adds a space between all characters in the grid.
 * Example
 * > O_OXXO_XX
 * ---------
 * | O _ O |
 * | X X O |
 * | _ X X |
 * ---------
 */
public class MainTictactoe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String board = scanner.nextLine();
        System.out.println(Board.getInstance().readBoard(board).toString());
        scanner.close();
    }

}

class Board {
    private static Board instance;
    private char[][] fields;

    private Board() {
        this.fields = new char[3][3];
    }

    static Board getInstance() {
        if (instance == null) return new Board();
        return instance;
    }

    Board readBoard(String board) {
        int rowNr = 0;
        for (char[] row : fields) {
            for (int i = 0; i < row.length; i++) {
                row[i] = board.charAt(rowNr * 3 + i);
            }
            rowNr++;
        }
        return this;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("---------\n");
        for (char[] row : fields) {
            sb.append("| " + new String(row).chars().mapToObj(i -> (char) i + " ").collect(Collectors.joining()) + "|\n");
        }
        sb.append("---------");
        return sb.toString();
    }
}
