package dev.manyroads.projects.tetris.example1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Figures figures = new Figures();

        String input = scanner.nextLine();
        Integer[][] figure = figures.getFigure(input);

        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0) {
                System.out.println();
            }
            System.out.print("- ");

        }
        System.out.println();

        for (int i = 0; i < 4; i++) {
            if (figure.length == 1) {
                break;
            } else {
                Figures.displayFigure(figure, i);
            }

        }
        Figures.displayFigure(figure, 0);
    }
}
