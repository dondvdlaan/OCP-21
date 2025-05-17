package dev.manyroads.projects.tetris.stage2.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pieceName = scanner.nextLine().trim();
        String[] dims = scanner.nextLine().trim().split(" ");
        int width = Integer.parseInt(dims[0]);
        int height = Integer.parseInt(dims[1]);

        GameController controller = new GameController(width, height);
        controller.initialize(pieceName);

        GameView view = new GameView();
        view.printGrids(controller.getGrids());

        while (true) {
            String command = scanner.nextLine().trim();
            if ("exit".equals(command)) {
                break;
            }
            controller.processCommand(command);
            view.printCurrentGrid(controller.getGrids());
        }
        scanner.close();
    }
}