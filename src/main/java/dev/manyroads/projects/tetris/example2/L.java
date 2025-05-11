package dev.manyroads.projects.tetris.example2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L extends Piece {
    private int order = -1;

    final int[] up = {1, 5, 9, 10};
    final int[] right =  {2, 4, 5, 6};
    final int[] down = {1, 2, 6, 10};
    final int[] left = {4, 5, 6, 8};
    List<int[]> rotation = new ArrayList<>(Arrays.asList(up, right, down, left));

    @Override
    public void rotateLeft() {
        if (order < 0) {
            printEmpty();
            order++;
            return;
        }
        print(rotation.get(order));
        order = --order < 0 ? 3 : order;
    }

    @Override
    public void rotateRight() {
        if (order < 0) {
            printEmpty();
            order++;
            return;
        }
        print(rotation.get(order));
        order = ++order % 4;
    }

    @Override
    public void rotateFull() {
        for (int i = 0; i < 6; i++) {
            rotateRight();
            System.out.println();
        }
    }
}
