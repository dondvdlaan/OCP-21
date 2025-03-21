package dev.manyroads.projects.gametictactoe.example;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class Field {

    private static Field instance;
    private final FieldValues[][] field;

    private Field() {
        field = new FieldValues[3][3];
    }

    public static Field getInstance() {
        if (instance == null) {
            instance = new Field();
        }
        return instance;
    }

    private void setMove(int x, int y, FieldValues value) {
        field[x][y] = value;
    }

    public void fillRandom() {
        Random random = new Random(new Date().getTime());
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                setMove(i, j, random.nextBoolean() ? FieldValues.CROSS : FieldValues.ZERO);
            }
        }
    }

    private void clear() {
        for (int i = 0; i < field.length; i++) {
            field[i] = new FieldValues[]{FieldValues.EMPTY, FieldValues.EMPTY, FieldValues.EMPTY};
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (FieldValues[] row : field) {
            sb.append(Arrays.stream(row).map(FieldValues::getValue).collect(Collectors.joining(" ")));
            sb.append("\r\n");
        }
        return sb.toString();
    }
}
