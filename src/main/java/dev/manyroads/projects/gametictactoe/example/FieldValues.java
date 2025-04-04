package dev.manyroads.projects.gametictactoe.example;

public enum FieldValues {

    CROSS("X"), ZERO("O"), EMPTY(".");

    private final String value;

    FieldValues(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
