package dev.manyroads.projects.tetris.stage4;

import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        new Tetris().initialize();
    }
}

class Tetris extends Pieces {
    Logger logger = Logger.getLogger(Tetris.class.getName());
    Scanner scanner = new Scanner(System.in);
    static int cols = 4;
    static int rows = 4;
    Integer[][] piece = null;
    static int rowCounter = 0;
    static int rotation = 0;
    static int horizontal = 0;
    static boolean isBottum = false;
    String[][] board = null;
    String[][] currentBoard = null;
    String[][] boardOccupied = null;

    void initialize() {
        logger.log(Level.INFO, "Started Tetris");
        String selection = scanner.next();
        cols = scanner.nextInt();
        rows = scanner.nextInt();
        board = createBoard(cols, rows);

        currentBoard = createBoard(cols, rows);
        System.out.println(toString(currentBoard));
        piece = switch (selection) {
            case "O" -> O;
            case "I" -> I;
            case "S" -> S;
            case "Z" -> Z;
            case "L" -> L;
            case "J" -> J;
            case "T" -> T;
            default -> throw new IllegalArgumentException("Invalid piece type");
        };
        currentBoard = initialBoard(currentBoard);
        System.out.println(toString(currentBoard));
        playTetris();
    }

    void playTetris() {
        while (true) {
            String selection = scanner.next();
            switch (selection) {
                case "rotate", "rr" -> {
                    if (!isBottum) rotation = ++rotation >= piece.length ? 0 : rotation;
                }
                case "left", "l" -> {
                    if (!isBottum) horizontal--;
                }
                case "right", "r" -> {
                    if (!isBottum) horizontal++;
                }
                case "down", "d" -> {
                }
                case "exit", "e" -> System.exit(0);
                default -> throw new IllegalArgumentException("Invalid piece type");
            }
            if (!isBottum) rowCounter++;
            currentBoard = renderBoard(currentBoard);
            System.out.println(toString(currentBoard));
        }
    }

    String[][] initialBoard(String[][] board) {
        int pos = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                for (int piecePos = 0; piecePos < piece[0].length; piecePos++) {
                    if (pos == piece[rotation][piecePos] + rowCounter * (cols)) {
                        board[row][col] = "0";
                    }
                }
                pos++;
            }
        }
        return board.clone();
    }

    String[][] renderBoard(String[][] currentBoard) {
        String[][] newBoard = createBoard(cols, rows);
        for (int row = 0; row < currentBoard.length; row++) {
            for (int col = 0; col < currentBoard[0].length; col++) {
                isBottum = isBottomReached();
                logger.log(Level.INFO, "" + isBottum);
                boolean isRightWallHit = isRightWallHit(row);
                for (int piecePos = 0; piecePos < piece[0].length; piecePos++) {
                    int pos = col + row * cols;
                    if (pos == piece[rotation][piecePos]) {
                        int restrictedCol = moveHorizontalWithRestrictions(col, isRightWallHit);
                        newBoard[row + rowCounter][restrictedCol] = "0";
                    }
                }
            }
        }
        return newBoard;
    }

    boolean isRightWallHit(int row) {
        return !Arrays.stream(piece[rotation]).allMatch(pos -> (pos % cols + horizontal) <= cols - 1);
    }

    boolean isBottomReached() {
        int exp1 = rowCounter * (cols - 1);
        int exp2 = (rows - 1) * (cols - 1);
        return !Arrays.stream(piece[rotation]).allMatch(pos -> (pos + exp1) < exp2);
    }

    int moveDownWithRestrictions(int row, boolean isBottomReached) {
        int pos = row + rowCounter;
        if (isBottomReached) {
            isBottum = true;
        }
//        if (pos > rows - 1) {
//            down = 0;
//            isBottum = true;
//            return --pos;
//        }
        return pos;
    }

    int moveHorizontalWithRestrictions(int col, boolean isRightWallHit) {
        int newCol = col + horizontal;
        if (newCol < 0) {
            return col + ++horizontal;
        }
        if (isRightWallHit) {
            horizontal--;
            return col + horizontal;
        }
        return newCol;
    }

    boolean checkLastRow(String[][] newBoard) {
        return !Arrays.asList(newBoard[rows - 1]).contains("0");
    }

    void updateBoardOccupied() {
        boardOccupied = board.clone();
        logger.log(Level.INFO, toString());
    }

    public String toString(String[][] board) {
        StringBuilder sb = new StringBuilder();
        for (String[] row : board) {
            for (String cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

abstract class Pieces {
    String[][] createBoard(int cols, int rows) {
        String[][] board = new String[rows][cols];
        for (String[] b : board) Arrays.fill(b, "-");
        return board;
    }

    Integer[][] blank = {{-1, -1, -1, -1}};

    Integer[][] O = {{4, 14, 15, 5}};
    Integer[][] I = {{4, 14, 24, 34}, {3, 4, 5, 6}};
    Integer[][] S = {{5, 4, 14, 13}, {4, 14, 15, 25}};
    Integer[][] Z = {{4, 5, 15, 16}, {5, 15, 14, 24}};
    Integer[][] L = {{4, 14, 24, 25}, {5, 15, 14, 13}, {4, 5, 15, 25}, {6, 5, 4, 14}};
    Integer[][] J = {{5, 15, 25, 24}, {15, 5, 4, 3}, {5, 4, 14, 24}, {4, 14, 15, 16}};
    Integer[][] T = {{4, 14, 24, 15}, {4, 13, 14, 15}, {5, 15, 25, 14}, {4, 5, 6, 15}};
}
