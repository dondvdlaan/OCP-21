package dev.manyroads.projects.asciimirror.stage5.example1.function;

import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public record ReversedString() implements UnaryOperator<String> {

    @Override
    public String apply(String line) {
        return new StringBuilder(line)
                .reverse()
                .chars()
                .map(new ReversedCodePoint())
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
    }
}
