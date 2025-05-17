package dev.manyroads.projects.tetris.stage2.example3;

class TetrisGrid {
    // Width of the grid
    private final int width;

    // Height of the grid
    private final int height;

    // 2D array representing the grid
    private final char[][] grid;

    // Constants representing empty and filled cells
    private static final char EMPTY = '-';
    private static final char FILLED = '0';

    // Constructor to initialize the Tetris grid with specified width and height
    public TetrisGrid(int width, int height) {
        this.width = width;
        this.height = height;

        // Initialize the grid array
        grid = new char[height][width];

        // Clear the grid to set all cells to empty
        clear();
    }

    // Method to clear the grid by setting all cells to empty
    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = EMPTY;
            }
        }
    }

    // Method to fill the grid with the given piece at specified offsets
    public void fill(Integer[] piece, int rowOffset, int colOffset) {
        for (int pos : piece) {
            // Calculate the row and column of the piece from its position
            int row = pos / 10;
            int col = pos % 10;

            // Calculate the new row and column based on the offsets
            int newRow = (row + rowOffset) % height;
            int newCol = (col + colOffset) % width;

            // Adjust for negative results due to modulo operation
            if (newRow < 0) {
                newRow += height;  // Wrap around to the bottom if newRow is negative
            }
            if (newCol < 0) {
                newCol += width;   // Wrap around to the right if newCol is negative
            }

            // Set the cell to filled
            grid[newRow][newCol] = FILLED;
        }
    }



    // Method to print the grid to the console
    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if a cell is occupied
    public boolean isOccupied(int row, int col) {
        return grid[row][col] == FILLED;
    }

    // Getter method for the grid width
    public int getWidth() {
        return width;
    }

    // Getter method for the grid height
    public int getHeight() {
        return height;
    }
}