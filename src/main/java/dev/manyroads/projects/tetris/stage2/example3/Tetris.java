package dev.manyroads.projects.tetris.stage2.example3;

import java.util.Scanner;

class Tetris {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read piece type
        String pieceInput = scanner.next().toUpperCase();

        // Read grid dimensions
        int width = scanner.nextInt();
        int height = scanner.nextInt();


        // Initialize the Tetris game
        TetrisGame game = new TetrisGame(width, height);
        game.handlePieceInput(pieceInput);

        // Command loop
        while (scanner.hasNext()) {
            String command = scanner.next().toLowerCase();
            if (command.equals("exit")) {
                break;
            }
            game.handleCommand(command);
        }

        scanner.close();
    }
}
