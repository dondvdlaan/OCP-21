package dev.manyroads.projects.tetris.example4;

public class Main {
    public static void main(String[] args) {
        var piece = Piece.valueOf(new java.util.Scanner(System.in).next());
        var baseGrid = "-".repeat(16);
        printGrid(baseGrid);
        for (int i = 0, n = 0; n < 5; i = ++i % piece.indices.length, n++) {
            var grid = new StringBuilder(baseGrid);
            for (int j = 0; j < piece.indices[i].length; j++) {
                int index = piece.indices[i][j];
                grid.replace(index, index + 1, "0");
            }
            printGrid(grid.toString());
        }
    }

    private static void printGrid(String grid) {
        System.out.println();
        for (int i = 0; i < grid.length(); i++) {
            System.out.printf("%s%s", grid.charAt(i), i % 4 == 3 ? "\n" : " ");
        }
    }
}

enum Piece {
    O(new int[][]{{5, 6, 9, 10}}),
    I(new int[][]{{1, 5, 9, 13}, {4, 5, 6, 7}}),
    S(new int[][]{{6, 5, 9, 8}, {5, 9, 10, 14}}),
    Z(new int[][]{{4, 5, 9, 10}, {2, 5, 6, 9}}),
    L(new int[][]{{1, 5, 9, 10}, {2, 4, 5, 6}, {1, 2, 6, 10}, {4, 5, 6, 8}}),
    J(new int[][]{{2, 6, 9, 10}, {4, 5, 6, 10}, {1, 2, 5, 9}, {0, 4, 5, 6}}),
    T(new int[][]{{1, 4, 5, 6}, {1, 4, 5, 9}, {4, 5, 6, 9}, {1, 5, 6, 9}});

    final int[][] indices;

    Piece(int[][] indices) {
        this.indices = indices;
    }
}