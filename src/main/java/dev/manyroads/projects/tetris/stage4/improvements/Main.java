package dev.manyroads.projects.tetris.stage4.improvements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

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
    static int rowCounter = -1;
    static int rotation = 0;
    static int horizontal = 0;
    static int direction = 0;
    static boolean isBottomOrOtherObjectTouched = false;
    static boolean isSelectNextPiece = false;
    String[][] currentBoard = null;
    Integer[] staticBoard = null;

    void initialize() {
        logger.log(Level.INFO, "Started Tetris");
        cols = scanner.nextInt();
        rows = scanner.nextInt();

        currentBoard = createBoard(cols, rows);
        staticBoard = new Integer[cols * rows];
        Arrays.fill(staticBoard, -1);
        System.out.println(toString(currentBoard));

        playTetris();
    }

    void playTetris() {
        while (true) {
            String move = scanner.next();
            switch (move) {
                case "piece", "p" -> {
                    piece = selectPiece(scanner.next());
                    isSelectNextPiece = false;
                }
                case "rotate", "rr" -> {
                    if (!isSelectNextPiece) rotation = ++rotation >= piece.length ? 0 : rotation;
                }
                case "left", "l" -> {
                    if (!isSelectNextPiece) {
                        direction = -1;
                        horizontal += direction;
                    }
                }
                case "right", "r" -> {
                    if (!isSelectNextPiece) {
                        direction = 1;
                        horizontal += direction;
                    }
                }
                case "down", "d" -> {
                }
                case "break", "b" -> {
                    staticBoard = removeLastRow(staticBoard);
                    logger.log(Level.INFO, Arrays.toString(staticBoard));
                }
                case "exit", "e" -> System.exit(0);
                default -> System.out.println("Invalid piece type, try again");
            }

            if (pieceDoesNotFit()) {
                System.out.println("Piece does not fit!");
                System.out.println("Game Over!");
                System.exit(0);
            }
            if (!isSelectNextPiece) {
                rowCounter++;
                currentBoard = renderBoard(currentBoard);
                System.out.println(toString(currentBoard));
            }
            if (isBottomOrOtherObjectTouched) {
                isSelectNextPiece = true;
                staticBoard = convertStringArrayToInteger(currentBoard);
                if (checkGameOver()) {
                    System.out.println("Game Over!");
                    System.exit(0);
                }
                rotation = 0;
                rowCounter = -1;
                horizontal = 0;
                direction = 0;
            }
            //if (isBottumReached) logger.log(Level.INFO, Arrays.toString(convertStringArrayToInteger(currentBoard)));
        }
    }

    String[][] renderBoard(String[][] currentBoard) {
        String[][] newBoard = createBoard(cols, rows);
//        logger.log(Level.INFO, "staticBoard " + Arrays.toString(staticBoard));
//        logger.log(Level.INFO, "piece[rotation] " + Arrays.toString(piece[rotation]));
//        logger.log(Level.INFO, "rowCounter: " + rowCounter);

        for (int row = 0; row < currentBoard.length; row++) {
            for (int col = 0; col < currentBoard[0].length; col++) {
                isBottomOrOtherObjectTouched = isBottomOrOtherObjectTouched(row);
                for (int piecePos = 0; piecePos < piece[0].length; piecePos++) {
                    if ((col + row * cols == piece[rotation][piecePos])) {
                        int restrictedCol = moveHorizontalWithRestrictions(col);
                        int restrictedRow = moveVerticalWithRestrictions(row);
                        newBoard[restrictedRow][restrictedCol] = "0";
                    }
                }
                if (staticBoard[col + row * cols] != null && (col + row * cols == staticBoard[col + row * cols]))
                    newBoard[row][col] = "0";
            }
        }
        return newBoard;
    }

    boolean isLeftWallHit() {
        return !Arrays.stream(piece[rotation]).allMatch(pos -> (pos % cols + horizontal) >= 0);
    }

    boolean isRightWallHit() {
        return !Arrays.stream(piece[rotation]).allMatch(pos -> (pos % cols + horizontal) <= (cols - 1));
    }

    boolean isBottomOrOtherObjectTouched(int row) {
        boolean isBottomReached = !Arrays.stream(piece[rotation])
                .allMatch(pos -> (pos + rowCounter * cols) <= rows * cols - 1);

        boolean isOtherObjectTouched = Arrays.stream(piece[rotation])
                .anyMatch(pos -> Arrays.stream(staticBoard)
                        .anyMatch(statPos -> statPos == (pos + horizontal + rowCounter * cols)));

        logger.log(Level.INFO, "isBottomReached: " + isBottomReached + " isOtherObjectTouched: " + isOtherObjectTouched);
        return isBottomReached || isOtherObjectTouched;
    }

    int moveHorizontalWithRestrictions(int col) {
        int newCol = col + horizontal;
        if (isBottomOrOtherObjectTouched) {
            logger.log(Level.INFO, "horizontal before: " + horizontal);
            if (direction > 0) {
                --horizontal;
                direction = 0;
            } else if (direction < 0) {
                ++horizontal;
                direction = 0;
            }
            logger.log(Level.INFO, "horizontal after: " + horizontal);
            return col + horizontal;
        }
        if (isLeftWallHit()) {
            return col + ++horizontal;
        }
        if (isRightWallHit()) {
            //horizontal--;
            return col + --horizontal;
        }
        direction = 0;
        return newCol;
    }

    int moveVerticalWithRestrictions(int row) {
        int newRow = row + rowCounter;
        if (isBottomOrOtherObjectTouched) {
            return --newRow;
        }
        return newRow;
    }

    Integer[] convertStringArrayToInteger(String[][] currentBoard) {
        Integer[] staticBoard = new Integer[rows * cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int pos = col + row * cols;
                staticBoard[pos] = "0".equals(currentBoard[row][col]) ? pos : -1;
                //if ("0".equals(currentBoard[row][col])) logger.log(Level.INFO, col + " " + row + " "+pos);
            }
        }
        return staticBoard;
    }

    Integer[][] selectPiece(String selection) {
        return switch (selection) {
            case "O", "o" -> O;
            case "I", "i" -> I;
            case "S", "s" -> S;
            case "Z", "z" -> Z;
            case "L", "l" -> L;
            case "J", "j" -> J;
            case "T", "t" -> T;
            default -> throw new IllegalArgumentException("Invalid piece type");
        };
    }

    boolean pieceDoesNotFit() {
        //logger.log(Level.INFO, "piece[rotation] " + Arrays.toString(piece[rotation]));
        //logger.log(Level.INFO, "staticBoard " + Arrays.toString(staticBoard));
        return Arrays.stream(staticBoard)
                .anyMatch(statPos -> Arrays.asList(piece[rotation]).contains(statPos));
    }

    boolean checkGameOver() {
        Boolean[] fullCol = new Boolean[rows];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int ind = r + c * cols;
                //logger.log(Level.INFO, "ind " + ind);
                fullCol[c] = staticBoard[ind] != -1;
            }
            if (Arrays.stream(fullCol).allMatch(Boolean::valueOf)) return true;
        }
        return false;
    }

    Integer[] removeLastRow(Integer[] staticBoard) {
        int start = (rows - 1) * cols, end = start + cols - 1;
        if (IntStream.rangeClosed(start, end).anyMatch(statPos -> Objects.equals(staticBoard[statPos], -1)))
            return staticBoard;
        Integer[] newStaticBoard = Arrays.copyOf(staticBoard, staticBoard.length - cols);
        List<Integer> temp = new ArrayList<>(Arrays.asList(newStaticBoard));
        for (int i = 1; i <= cols; i++) {
            temp.addFirst(-1);
        }
        Integer[] temp2 = temp.stream()
                .map(i -> {
                    if (!Objects.equals(i, -1)) return i + cols;
                    return i;
                }).toArray(Integer[]::new);
        return temp2;
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

    Integer[][] O = {{4, 14, 15, 5}};
    Integer[][] I = {{4, 14, 24, 34}, {3, 4, 5, 6}};
    Integer[][] S = {{5, 4, 14, 13}, {4, 14, 15, 25}};
    Integer[][] Z = {{4, 5, 15, 16}, {5, 15, 14, 24}};
    Integer[][] L = {{4, 14, 24, 25}, {5, 15, 14, 13}, {4, 5, 15, 25}, {6, 5, 4, 14}};
    Integer[][] J = {{5, 15, 25, 24}, {15, 5, 4, 3}, {5, 4, 14, 24}, {4, 14, 15, 16}};
    Integer[][] T = {{4, 14, 24, 15}, {4, 13, 14, 15}, {5, 15, 25, 14}, {4, 5, 6, 15}};
}
