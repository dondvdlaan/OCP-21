package dev.manyroads.projects.tetris.stage2.example3;

class TetrisGame {
    // Tetris grid representing the game board
    private TetrisGrid grid;

    // Current Tetris piece
    private TetrisPiece piece;

    // Current position of the piece
    private int pieceRow;
    private int pieceCol;

    // Current rotation state of the piece
    private int currentState;

    // Constructor to initialize the Tetris game with specified width and height
    public TetrisGame(int width, int height) {
        // Initialize the grid with the given dimensions
        grid = new TetrisGrid(width, height);

        // Initialize the piece position (top center of the grid)
        pieceRow = 0;
        pieceCol = width / 2 - 5; // Center the piece horizontally

        // Initialize the rotation state of the piece
        currentState = 0;
    }

    // Method to handle the input for selecting a Tetris piece
    public void handlePieceInput(String pieceInput) {
        // Print the empty grid
        printEmptyGrid();

        try {
            // Convert the input string to a TetrisPiece enumeration
            piece = TetrisPiece.valueOf(pieceInput);
        } catch (IllegalArgumentException e) {
            // If the input is invalid, print an error message
            System.out.println("Invalid piece");
            return;
        }

        // Print the grid with the current piece
        printGridWithPiece();
        System.out.println();
    }

    // Method to handle movement commands for the current piece
    public void handleCommand(String command) {
        switch (command) {
            case "left":
                moveLeft();
                moveDown();
                break;
            case "right":
                moveRight();
                moveDown();
                break;
            case "down":
                moveDown();
                break;
            case "rotate":
                rotatePiece();
                moveDown();
                break;
            default:
                // If the command is unknown, print an error message
                System.out.println("Unknown command");
                break;
        }
        // Print the grid with the current piece after the command
        printGridWithPiece();
        System.out.println();
    }

    // Method to move the piece to the left
    private void moveLeft() {
        pieceCol--;
    }

    // Method to move the piece to the right
    private void moveRight() {
        pieceCol++;
    }

    // Method to move the piece down
    private void moveDown() {
        pieceRow++;
    }

    // Method to rotate the piece
    private void rotatePiece() {
        currentState = (currentState + 1) % piece.getStates().length;
    }

    // Method to print the empty grid
    private void printEmptyGrid() {
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth(); j++) {
                System.out.print("- ");
            }
            System.out.println();
        }
        System.out.println(); // Print a blank line to separate the grids
    }

    // Method to print the grid with the current piece in its position
    private void printGridWithPiece() {
        // Clear the grid
        grid.clear();

        // Fill the grid with the current piece in its current state and position
        grid.fill(piece.getStates()[currentState], pieceRow, pieceCol);

        // Print the grid to the console
        grid.print();
    }
}
