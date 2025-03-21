package dev.manyroads.projects.gametictactoe.stage1;

/**
 * Your first task in this project is to print the game grid in the console output.
 * Use what you’ve learned about the print() function to print three lines,
 * each containing three characters (X’s and O’s) to represent a game of
 * tic-tac-toe where all fields of the grid have been filled in.
 * <p>
 * Example
 * The example below shows how your output might look:
 * X O X
 * O X O
 * X X O
 */
public class MainTictactoe {

    public static void main(String[] args) {
        System.out.println(Board.getInstance().toString());
    }

}

class Board {
    private static Board instance;
    private String[][] fields;

    private Board() {
        this.fields = new String[3][3];
    }

    static Board getInstance() {
        if (instance == null) return new Board();
        return instance;
    }

    @Override
    public String toString() {
        return """
                X O X
                O X O
                X X O""";
    }
}
