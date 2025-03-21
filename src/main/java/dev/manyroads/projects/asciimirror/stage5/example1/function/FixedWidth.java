package dev.manyroads.projects.asciimirror.stage5.example1.function;

import java.util.function.UnaryOperator;

public record FixedWidth(int width) implements UnaryOperator<String> {

    @Override
    public String apply(String line) {
        return (line + " ".repeat(width)).substring(0, width);
    }
}
