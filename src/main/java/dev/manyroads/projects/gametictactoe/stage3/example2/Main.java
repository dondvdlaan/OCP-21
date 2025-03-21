package dev.manyroads.projects.gametictactoe.stage3.example2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static dev.manyroads.projects.gametictactoe.stage3.example2.BoardItem.toBoardItem;


class Board {
    BoardItem[][] board;

    Board(char[] boardState) {
        this(3, 3, boardState);
    }

    Board(int rows, int cols, char[] boardState) {
        board = new BoardItem[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = toBoardItem(boardState[i * rows + j]);
            }
        }
    }

    public int[] getTypeNumberOfBoardItem() {
        return Arrays.stream(board).map(row -> {
            int[] counts = new int[3];
            counts[0] += Arrays.stream(row).filter(boardItem -> boardItem.equals(BoardItem.X)).count();
            counts[1] += Arrays.stream(row).filter(boardItem -> boardItem.equals(BoardItem.O)).count();
            counts[2] += Arrays.stream(row).filter(boardItem -> boardItem.equals(BoardItem.EMPTY)).count();
            return counts;
        }).reduce((a, b) -> {
            a[0] += b[0];
            a[1] += b[1];
            a[2] += b[2];
            return a;
        }).orElse(new int[3]);
    }

    private int[][] getBoardItemsInARow() {
        int[] countsHorizontal = new int[3];
        for (int i = 0; i < board.length; i++) {
            BoardItem item = board[i][0];
            for (int j = 0; j < board[i].length; j++) {
                if (item != board[i][j]) {
                    break;
                }
                countsHorizontal[i]++;
            }
        }

        int[] countsVertical = new int[3];
        for (int i = 0; i < board.length; i++) {
            BoardItem item = board[0][i];
            for (BoardItem[] boardItems : board) {
                if (item != boardItems[i]) {
                    break;
                }
                countsVertical[i]++;
            }
        }
        return new int[][]{countsHorizontal, countsVertical};
    }

    public boolean isDiagonalWinning() {
        BoardItem initial = board[0][0];
        for (int i = 0; i < board.length; i++) {
            if (!board[i][i].equals(initial)) {
                break;
            }
            if (i == board.length - 1) {
                return true;
            }
        }

        initial = board[board.length - 1][0];
        int i = board.length - 1;
        for (BoardItem[] boardItems : board) {
            if (!boardItems[i--].equals(initial)) {
                return false;
            }
        }

        return true;
    }

    public String getResult() {
        int[] countBoardItems = getTypeNumberOfBoardItem();
        if (Math.abs(countBoardItems[0] - countBoardItems[1]) > 1) {
            return "Impossible";
        }

        if (isDiagonalWinning()) {
            return board[0][0] + " wins";
        }
        int[][] boardItemsInARow = getBoardItemsInARow();

        Map<Integer, int[]> results = new HashMap<>();
        for (int i = 0; i < boardItemsInARow.length; i++) {
            for (int j = 0; j < boardItemsInARow[i].length; j++) {
                if (boardItemsInARow[i][j] == boardItemsInARow[i].length) {
                    if (results.containsKey(i)) {
                        return "Impossible";
                    }
                    int[] winnerPosition = {i, j};
                    results.put(i, winnerPosition);
                }
            }
        }

        if (!results.isEmpty()) {
            int[] pos = results.values().stream().findFirst().orElse(null);
            return board[pos[0]][pos[1]].toString() + " wins";
        }

        if (countBoardItems[2] > 0) {
            return "Game not finished";
        }

        return "Draw";
    }

    public void print() {
        System.out.println("---------");

        Arrays.stream(board).forEach(row -> {
            String stringRow = Arrays
                    .stream(row)
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            System.out.printf("| %s |\n", stringRow);
        });

        System.out.println("---------");
    }
}


public class Main {

    public static void main(String[] args) {
        System.out.print("Enter cells: ");
        Board board = new Board(new Scanner(System.in).next().toCharArray());
        board.print();
        System.out.println(board.getResult());
    }
}
