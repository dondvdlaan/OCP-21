package dev.manyroads.projects.tetris.stage2.example;

public class Grid {
    private final String[][] matrix;

    public Grid(int rows, int cols) {
        matrix = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = "-";
            }
        }
    }

    public void setCell(int row, int col, String value) {
        matrix[row][col] = value;
    }

    public String[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String[] row : matrix) {
            for (String cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}
