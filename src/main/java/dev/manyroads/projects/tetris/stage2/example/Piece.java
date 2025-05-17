package dev.manyroads.projects.tetris.stage2.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Piece {
    private final String name;
    private final Integer[][] rotations;

    public Piece(String name, Integer[][] rotations) {
        this.name = name;
        this.rotations = rotations;
    }

    public String getName() {
        return name;
    }

    public List<Integer[]> getRotations() {
        return new ArrayList<>(Arrays.asList(rotations));
    }
}
