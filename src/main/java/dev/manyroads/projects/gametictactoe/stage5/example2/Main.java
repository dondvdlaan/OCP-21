package dev.manyroads.projects.gametictactoe.stage5.example2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        game.printBoard();

        while (true) {
            System.out.println("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            String[] parts = coordinates.split(" ");

            // Check if the input is valid
            if (parts.length != 2) {
                System.out.println("You should enter two numbers!");
                continue;
            }

            int row, col;
            try {
                row = Integer.parseInt(parts[0]);
                col = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            // Check if the coordinates are within the grid
            if (row < 1 || row > 3 || col < 1 || col > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            // Adjust for 0-based indexing
            row--;
            col--;

            // Check if the cell is occupied
            if (game.isCellOccupied(row, col)) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            // Make the move
            game.makeMove(row, col);
            game.printBoard();

            // Check the game state
            String result = game.checkGameState();
            if (!result.equals("Game not finished")) {
                System.out.println(result);
                break;
            }
        }

        scanner.close();
    }
}

class TicTacToe {
    private final char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public boolean isCellOccupied(int row, int col) {
        return board[row][col] != ' ';
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public String checkGameState() {
        if (checkWin('X')) {
            return "X wins";
        } else if (checkWin('O')) {
            return "O wins";
        } else if (isDraw()) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }

    private boolean checkWin(char player) {
        // Check rows, columns, and diagonals
        return (board[0][0] == player && board[0][1] == player && board[0][2] == player) ||
                (board[1][0] == player && board[1][1] == player && board[1][2] == player) ||
                (board[2][0] == player && board[2][1] == player && board[2][2] == player) ||
                (board[0][0] == player && board[1][0] == player && board[2][0] == player) ||
                (board[0][1] == player && board[1][1] == player && board[2][1] == player) ||
                (board[0][2] == player && board[1][2] == player && board[2][2] == player) ||
                (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
