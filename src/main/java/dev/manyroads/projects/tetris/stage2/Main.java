package dev.manyroads.projects.tetris.stage2;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Main.class.getName());
        logger.log(Level.INFO, "Hello " + logger.getName());
        new Tetris().start();
    }
}

class Tetris extends Pieces {
    Logger logger = Logger.getLogger(Tetris.class.getName());

    static int cols = 4;
    static int rows = 4;
    Integer[][] piece = null;
    static int down = -1;
    static int rotation = 0;
    static int horizontal = 0;

    void start() {
        logger.log(Level.INFO, "Started Tetris");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();
        cols = scanner.nextInt();
        rows = scanner.nextInt();

        piece = blank;
        printTetris();
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
        printTetris();
        while (true) {
            selection = scanner.next();
            switch (selection) {
                case "rotate" -> rotatePiece();
                case "left" -> movePieceLeft();
                case "right" -> movePieceRight();
                case "down" -> printTetris();
                case "exit" -> System.exit(0
                );
                default -> throw new IllegalArgumentException("Invalid piece type");
            }
        }
    }

    void rotatePiece() {
        rotation = ++rotation >= piece.length ? 0 : rotation;
        printTetris();
    }

    void movePieceLeft() {
        horizontal--;
        printTetris();
    }

    void movePieceRight() {
        horizontal++;
        printTetris();
    }

    void printTetris() {
        String[][] board = createBoard(cols, rows);
        System.out.println();
        int pos = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (pos == piece[rotation][0] || pos == piece[rotation][1] || pos == piece[rotation][2] || pos == piece[rotation][3]) {
                    board[noVericalBorderRestrictions(r, down)][noHorizonBorderRestrictions(c, horizontal)] = "0";
                }
                System.out.print(board[r][c] + " ");
                pos++;
            }
            System.out.println();
        }
        down++;
    }

    int noHorizonBorderRestrictions(int col, int horizontal) {
        int pos = col + horizontal;
        if (pos < 0) {
            double absPos = Math.abs(pos);
            double temp = Math.ceil(absPos / cols);
            double correctedPos = temp * cols + pos;
            return (int) correctedPos;
        }
        if (pos >= cols) {
            return pos - (int) Math.floor(pos / cols) * cols;
        }
        return pos;
    }

    int noVericalBorderRestrictions(int row, int down) {
        int pos = row + down;
        if (pos >= rows) return rows - 1;
        return pos;
    }
}

abstract class Pieces {
    String[][] createBoard(int cols, int rows) {
        String[][] board = new String[rows][cols];
        for (int c = 0; c < board[0].length; c++) {
            for (int r = 0; r < board.length; r++) {
                board[r][c] = "-";
            }
        }
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
