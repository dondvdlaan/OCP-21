package dev.manyroads.projects.tetris.stage2.example;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final int width;
    private final int height;
    private final List<Grid> grids = new ArrayList<>();
    private List<Integer[]> rotations;
    private int rotationIndex = 0;
    private Integer[] currentPositions;

    public GameController(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void initialize(String pieceName) {
        // 1) Add empty board
        grids.add(createBoard(new Integer[]{}));

        // 2) Set up piece
        Piece piece = getPieceByName(pieceName);
        if (piece != null && !piece.getRotations().isEmpty()) {
            rotations = piece.getRotations();
            rotationIndex = 0;
            currentPositions = rotations.get(rotationIndex);
            // 3) Add board with piece
            grids.add(createBoard(currentPositions));
        }
    }

    public void processCommand(String command) {
        switch (command) {
            case "left" -> move(-1);
            case "right" -> move(1);
            case "down" -> move(width);
            case "rotate" -> rotate();
            default -> {}
        }
    }

    private void move(int delta) {
        if (currentPositions == null) return;
        Integer[] newPos = new Integer[currentPositions.length];
        for (int i = 0; i < currentPositions.length; i++) {
            newPos[i] = currentPositions[i] + delta;
        }
        currentPositions = newPos;
        grids.add(createBoard(currentPositions));
    }

    private void rotate() {
        if (rotations == null || rotations.isEmpty()) return;
        rotationIndex = (rotationIndex + 1) % rotations.size();
        currentPositions = rotations.get(rotationIndex);
        grids.add(createBoard(currentPositions));
    }

    public List<Grid> getGrids() {
        return grids;
    }

    private Grid createBoard(Integer[] positions) {
        Grid board = new Grid(height, width);
        for (int pos : positions) {
            int r = pos / width;
            int c = pos % width;
            if (r >= 0 && r < height && c >= 0 && c < width) {
                board.setCell(r, c, "0");
            }
        }
        return board;
    }

    private Piece getPieceByName(String name) {
        return switch (name) {
            case "O" -> new Piece("O", new Integer[][]{
                    {4, 14, 15, 5}
            });
            case "I" -> new Piece("I", new Integer[][]{
                    {4, 14, 24, 34}, {3, 4, 5, 6}
            });
            case "S" -> new Piece("S", new Integer[][]{
                    {5, 4, 14, 13}, {4, 14, 15, 25}
            });
            case "Z" -> new Piece("Z", new Integer[][]{
                    {4, 5, 15, 16}, {5, 15, 14, 24}
            });
            case "L" -> new Piece("L", new Integer[][]{
                    {4, 14, 24, 25},
                    {5, 15, 14, 13},
                    {4, 5, 15, 25},
                    {6, 5, 4, 14}
            });
            case "J" -> new Piece("J", new Integer[][]{
                    {5, 15, 25, 24},
                    {15, 5, 4, 3},
                    {5, 4, 14, 24},
                    {4, 14, 15, 16}
            });
            case "T" -> new Piece("T", new Integer[][]{
                    {4, 14, 24, 15},
                    {4, 13, 14, 15},
                    {5, 15, 25, 14},
                    {4, 5, 6, 15}
            });
            default -> null;
        };
    }
}
