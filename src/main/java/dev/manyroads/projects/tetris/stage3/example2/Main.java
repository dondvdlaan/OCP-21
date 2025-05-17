package dev.manyroads.projects.tetris.stage3.example2;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var sc = new Scanner(System.in).useDelimiter("\n");
        var piece = Piece.valueOf(sc.next());
        var size = Arrays.stream(sc.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        int cols = size[0];
        int rows = size[1];
        var baseGrid = "-".repeat(cols * rows);
        var grid = new StringBuilder(baseGrid);
        printGrid(baseGrid, cols);
        var action = "";
        for (int i = 0, row = 0; ; row++) {
            if (row != 0) action = sc.next();
            if ("rotate".equals(action)) i = ++i % piece.indices.length;
            if ("exit".equals(action)) System.exit(0);
            int currentRow = row;
            var isTouchingLeft = Arrays.stream(piece.indices[i]).anyMatch(e -> e % cols == 0);
            var isTouchingRight = Arrays.stream(piece.indices[i]).anyMatch(e -> e % cols == cols - 1);
            var isTouchingBottom = Arrays.stream(piece.indices[i]).anyMatch(e -> e + (currentRow - 1) * cols >= rows * cols - cols);
            grid = new StringBuilder(isTouchingBottom ? grid : baseGrid);
            for (int j = 0; j < 4; j++) {
                if ("right".equals(action) && !isTouchingRight || "left".equals(action) && !isTouchingLeft) {
                    for (int k = 0; k < piece.indices.length; k++) {
                        piece.indices[k][j] += "right".equals(action) ? 1 : -1;
                    }
                }
                if (!isTouchingBottom) {
                    int index = piece.indices[i][j] + row * cols;
                    grid.replace(index, index + 1, "0");
                }
            }
            printGrid(grid.toString(), cols);
        }
    }

    private static void printGrid(String grid, int cols) {
        for (int i = 0; i < grid.length(); i++) {
            System.out.printf("%s%s", grid.charAt(i), i % cols == cols - 1 ? "\n" : " ");
        }
        System.out.println();
    }
}

enum Piece {
    O(new int[][]{{4, 14, 15, 5}}),
    I(new int[][]{{4, 14, 24, 34}, {3, 4, 5, 6}}),
    S(new int[][]{{5, 4, 14, 13}, {4, 14, 15, 25}}),
    Z(new int[][]{{4, 5, 15, 16}, {5, 15, 14, 24}}),
    L(new int[][]{{4, 14, 24, 25}, {5, 15, 14, 13}, {4, 5, 15, 25}, {6, 5, 4, 14}}),
    J(new int[][]{{5, 15, 25, 24}, {15, 5, 4, 3}, {5, 4, 14, 24}, {4, 14, 15, 16}}),
    T(new int[][]{{4, 14, 24, 15}, {4, 13, 14, 15}, {5, 15, 25, 14}, {4, 5, 6, 15}});

    final int[][] indices;

    Piece(int[][] indices) {
        this.indices = indices;
    }
}
