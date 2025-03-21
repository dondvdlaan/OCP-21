package dev.manyroads.projects.gametictactoe.stage5.example;

public class TicTacToe {
    private TicTacToeStatus status;

    private char[][] cells;
    private char symbol;

    private int roundCount;

    public TicTacToe() {
        this.status = TicTacToeStatus.DNF;
        this.cells = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        this.symbol = 'X';
        this.roundCount = 0;
    }

    private void checkRow(int row) {
        if (cells[row][0] == cells[row][1] && cells[row][1] == cells[row][2]) {
            if (status == TicTacToeStatus.DNF) {
                if (cells[row][0] == 'X') {
                    status = TicTacToeStatus.X_WINS;
                } else if (cells[row][0] == 'O') {
                    status = TicTacToeStatus.O_WINS;
                }
            }
        }
    }

    private void checkColumn(int column) {
        if (cells[0][column] == cells[1][column] && cells[1][column] == cells[2][column]) {
            if (status == TicTacToeStatus.DNF) {
                if (cells[0][column] == 'X') {
                    status = TicTacToeStatus.X_WINS;
                } else if (cells[0][column] == 'O') {
                    status = TicTacToeStatus.O_WINS;
                }
            }
        }
    }

    private void checkDiagonals() {
        if (cells[0][0] == cells[1][1] && cells[1][1] == cells[2][2] || cells[0][2] == cells[1][1] && cells[1][1] == cells[2][0]) {
            if (status == TicTacToeStatus.DNF) {
                if (cells[1][1] == 'X') {
                    status = TicTacToeStatus.X_WINS;
                } else if (cells[1][1] == 'O') {
                    status = TicTacToeStatus.O_WINS;
                }
            }
        }
    }

    public void checkDraw() {
        if (status == TicTacToeStatus.DNF && roundCount == 9) {
            status = TicTacToeStatus.DRAW;
        }
    }

    public void setCell(int x, int y) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        int i = 3 - y;
        int j = x - 1;

        if (cells[i][j] != ' ') {
            throw new IllegalArgumentException("Coordinate is occupied!");
        }

        cells[i][j] = symbol;
        roundCount++;

        checkRow(i);
        checkColumn(j);
        checkDiagonals();
        checkDraw();

        symbol = symbol == 'X' ? 'O' : 'X';
    }

    public String getStatusMessage() {
        switch (status) {
            case DNF:
                return "Game not finished";
            case X_WINS:
                return "X wins";
            case O_WINS:
                return "O wins";
            case DRAW:
                return "Draw";
            default:
                return "Impossible";
        }
    }

    public String getStatusMessage2() {
        return switch (status) {
            case DNF -> "Game not finished";
            case X_WINS -> "X wins";
            case O_WINS -> "O wins";
            case DRAW -> "Draw";
            default -> "Impossible";
        };
    }

    public boolean isFinished() {
        return status != TicTacToeStatus.DNF;
    }

    @Override
    public String toString() {
        return String.format("---------%n" +
                "| %c %c %c |%n" +
                "| %c %c %c |%n" +
                "| %c %c %c |%n" +
                "---------", cells[0][0], cells[0][1], cells[0][2], cells[1][0], cells[1][1], cells[1][2], cells[2][0], cells[2][1], cells[2][2]);
    }
}

enum TicTacToeStatus {
    DNF,
    X_WINS,
    O_WINS,
    DRAW
}
