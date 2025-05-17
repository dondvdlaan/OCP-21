package dev.manyroads.projects.tetris.stage2.example3;

// Enumeration representing different types of Tetris pieces
enum TetrisPiece {
    // O-shaped piece with a single rotation state
    O(new Integer[][]{{4, 14, 15, 5}}),

    // I-shaped piece with two rotation states
    I(new Integer[][]{{4, 14, 24, 34}, {3, 4, 5, 6}}),

    // S-shaped piece with two rotation states
    S(new Integer[][]{{5, 4, 14, 13}, {4, 14, 15, 25}}),

    // Z-shaped piece with two rotation states
    Z(new Integer[][]{{4, 5, 15, 16}, {5, 15, 14, 24}}),

    // L-shaped piece with four rotation states
    L(new Integer[][]{{4, 14, 24, 25}, {5, 15, 14, 13}, {4, 5, 15, 25}, {6, 5, 4, 14}}),

    // J-shaped piece with four rotation states
    J(new Integer[][]{{5, 15, 25, 24}, {15, 5, 4, 3}, {5, 4, 14, 24}, {4, 14, 15, 16}}),

    // T-shaped piece with four rotation states
    T(new Integer[][]{{4, 14, 24, 15}, {4, 13, 14, 15}, {5, 15, 25, 14}, {4, 5, 6, 15}});

    // Array holding the different rotation states of the piece
    private final Integer[][] states;

    // Constructor to initialize the piece with its rotation states
    TetrisPiece(Integer[][] states) {
        this.states = states;
    }

    // Method to get the rotation states of the piece
    public Integer[][] getStates() {
        return states;
    }
}
