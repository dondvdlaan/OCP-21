package dev.manyroads.projects.tetris.stage4.improvements2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Grid {
    private final int cols;
    private final int rows;
    private Field[][] grid;
    private Piece piece;

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        this.grid = new Field[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Field(i, j, FieldStatus.FREE);
            }
        }
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setGrid(Field[][] grid) {
        this.grid = grid;
    }

    public void printMovingPieceOnTheGrid(List<Integer> state) {
        Collections.sort(state);
        int sCount = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (state.get(sCount) == (row * cols + col)) {
                    System.out.print("0 ");
                    if (sCount < 3) {
                        sCount++;
                    }
                } else {
                    System.out.print(grid[row][col].getFieldStatus().getSymbol() + " ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].getFieldStatus().getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public List<Integer> down() {
        for (int i = 0; i < piece.getCurrentState().size(); i++) {
            piece.setCurrentState(i, piece.getCurrentState().get(i) + cols);

        }
        piece.setCurrentRow();
        isDown();
        isTouchingAnotherPiece();
        return piece.getCurrentState();
    }

    public List<Integer> rotate() {
        int newStateIndex;
        if (piece.getStateIndex() == this.piece.getStates().length - 1) {
            newStateIndex = 0;
        } else {
            newStateIndex = piece.getStateIndex() + 1;
        }
        List<Integer> baseRotatedState = Arrays.asList(piece.getStates()[newStateIndex]);
        for (int i = 0; i < piece.getCurrentState().size(); i++) {
            piece.setCurrentState(i, baseRotatedState.get(i) + (piece.getCurrentRow() + 1) * cols + piece.getCurrentCol());
        }
        piece.setStateIndex(newStateIndex);
        piece.setCurrentRow();
        isDown();
        isTouchingAnotherPiece();
        return piece.getCurrentState();
    }

    public List<Integer> right() {
        boolean boundry = false;
        for (Integer state : piece.getCurrentState()) {
            if (state % 10 == 9) {
                boundry = true;
            }
        }
        for (int i = 0; i < piece.getCurrentState().size(); i++) {
            Integer newState = null;
            if (boundry) {
                newState = piece.getCurrentState().get(i) + cols;
            } else {
                newState = piece.getCurrentState().get(i) + cols + 1;
            }
            piece.setCurrentState(i, newState);
        }
        piece.setCurrentRow();
        if (piece.getCurrentCol() == 5) {
            piece.setCurrentCol(-4);
        } else {
            piece.setCurrentCol(piece.getCurrentCol() + 1);
        }
        isDown();
        isTouchingAnotherPiece();
        return piece.getCurrentState();
    }

    public List<Integer> left() {
        boolean boundry = false;
        for (Integer state : piece.getCurrentState()) {
            if (state % 10 == 0) {
                boundry = true;
            }
        }

        for (int i = 0; i < piece.getCurrentState().size(); i++) {
            Integer newState = null;
            if (boundry) {
                newState = piece.getCurrentState().get(i) + cols;
            } else {
                newState = piece.getCurrentState().get(i) + cols - 1;
            }
            piece.setCurrentState(i, newState);
        }
        piece.setCurrentRow();
        if (piece.getCurrentCol() == -4) {
            piece.setCurrentCol(5);
        } else {
            piece.setCurrentCol(piece.getCurrentCol() - 1);
        }
        isDown();
        isTouchingAnotherPiece();
        return piece.getCurrentState();
    }

    public void isDown() {
        if (!piece.isBottom()) {
            for (Integer state : piece.getCurrentState()) {
                String stateString = String.valueOf(state);
                if (stateString.length() > 1 && ("" + stateString.charAt(0)).equals(String.valueOf(rows - 1))) {
                    piece.setBottom(true);
                    updateGrid();
                    break;
                }
            }
        }
    }

    //isPieceTouching down border or some other piece
    public void isTouchingAnotherPiece() {
        if (!piece.isBottom()) {
            for (Integer state : piece.getCurrentState()) {
                int i = state / cols;
                int j = state % cols;
                if (grid[i + 1][j].getFieldStatus() == FieldStatus.TAKEN) {
                    piece.setBottom(true);
                    updateGrid();
                    break;
                }
            }
        }
    }

    public void updateGrid() {
        Collections.sort(this.piece.getCurrentState());
        int sCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.piece.getCurrentState().get(sCount) == (i * cols + j)) {
                    this.grid[i][j].setFieldStatus(FieldStatus.TAKEN);
                    if (sCount < 3) {
                        sCount++;
                    }
                }
            }
        }
    }


    public void breakkkk() {
        List<Integer> indexesOfFullRows = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            boolean rowFull = true;
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].getFieldStatus() == FieldStatus.FREE) {
                    rowFull = false;
                    break;
                }
            }
            if (rowFull) {
                indexesOfFullRows.add(i);
            }
        }

        List<Field[]> listOfRows = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            listOfRows.add(grid[i]);
        }
        Collections.sort(indexesOfFullRows);
        Collections.reverse(indexesOfFullRows);
        for (Integer index : indexesOfFullRows) {
            listOfRows.remove(index.intValue());
        }

        Field[][] newGr = new Field[rows][cols];
        for (int i = 0; i < indexesOfFullRows.size(); i++) {
            for (int j = 0; j < cols; j++) {
                newGr[i][j] = new Field(i, j, FieldStatus.FREE);
            }
        }

        for (int i = 0; i < listOfRows.size(); i++) {
            for (int j = 0; j < cols; j++) {
                if (listOfRows.get(i)[j].getFieldStatus() == FieldStatus.TAKEN) {
                    newGr[i + indexesOfFullRows.size()][j] = new Field(i + indexesOfFullRows.size(), j, FieldStatus.TAKEN);
                } else {
                    newGr[i + indexesOfFullRows.size()][j] = new Field(i + indexesOfFullRows.size(), j, FieldStatus.FREE);
                }
            }
        }

        setGrid(newGr);
        printGrid();
    }

    public boolean isGameOver() {
        boolean gameOver = false;
        for (int j = 0; j < cols; j++) {
            boolean colFilled = true;
            for (int i = 0; i < rows; i++) {
                if (grid[i][j].getFieldStatus() == FieldStatus.FREE) {
                    colFilled = false;
                }
            }
            if (colFilled) {
                gameOver = colFilled;
                break;
            }
        }
        return gameOver;
    }
}


