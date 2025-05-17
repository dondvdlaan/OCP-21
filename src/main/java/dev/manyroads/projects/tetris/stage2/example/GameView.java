package dev.manyroads.projects.tetris.stage2.example;


import java.util.List;

public class GameView {
    public void printGrids(List<Grid> grids) {
        for (int i = 0; i < grids.size(); i++) {
            System.out.println(grids.get(i));
            System.out.println();
        }
    }

    public void printCurrentGrid(List<Grid> grids) {
        if (!grids.isEmpty()) {
            System.out.println(grids.get(grids.size() - 1));
            System.out.println();
        }
    }
}
