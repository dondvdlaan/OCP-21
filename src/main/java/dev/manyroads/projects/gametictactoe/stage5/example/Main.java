package dev.manyroads.projects.gametictactoe.stage5.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToe ticTacToe = new TicTacToe();

        System.out.println(ticTacToe);

        while (!ticTacToe.isFinished()) {
            System.out.print("Enter the coordinates: ");
            try {
                int x = Integer.parseInt(scanner.next());
                int y = Integer.parseInt(scanner.next());

                ticTacToe.setCell(x, y);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            System.out.println(ticTacToe);
        }

        System.out.println(ticTacToe.getStatusMessage());
    }
}
