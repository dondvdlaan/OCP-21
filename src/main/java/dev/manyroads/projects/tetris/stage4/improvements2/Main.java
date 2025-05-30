package dev.manyroads.projects.tetris.stage4.improvements2;


import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main extends Gear {

    public static void main(String[] args) {
        Map<String, Integer[][]> pieceMap = setPieceMap();
        Scanner scanner = new Scanner(System.in);
        int cols = scanner.nextInt();
        int rows = scanner.nextInt();

        Grid grid = new Grid(cols, rows);
        grid.printGrid();

        while (true) {
            String command = scanner.next();
            if (grid.isGameOver()) {
                grid.printGrid();
                System.out.println("Game over!");
                break;
            } else {
                if (command.matches("piece")) {
                    String input = scanner.next();
                    Integer[][] states = pieceMap.get(input);
                    grid.printMovingPieceOnTheGrid(Arrays.asList(states[0]));
                    grid.setPiece(new Piece("piece", states));
                    grid.isTouchingAnotherPiece();
                } else if (command.matches("exit")) {
                    break;
                } else if (command.matches("break")) {
                    //System.out.println("breaking in Main");
                    grid.breakkkk();
                } else if (grid.getPiece().isBottom()) {
                    grid.printGrid();
                    // System.out.println("piece bottom");
                } else if (command.matches("down")) {
                    grid.printMovingPieceOnTheGrid(grid.down());
                } else if (command.matches("rotate")) {
                    grid.printMovingPieceOnTheGrid(grid.rotate());
                } else if (command.matches("right")) {
                    grid.printMovingPieceOnTheGrid(grid.right());
                } else if (command.matches("left")) {
                    grid.printMovingPieceOnTheGrid(grid.left());
                }
            }
        }
    }
}
