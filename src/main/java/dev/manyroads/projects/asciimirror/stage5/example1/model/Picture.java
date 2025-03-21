package dev.manyroads.projects.asciimirror.stage5.example1.model;

import java.util.List;
import java.util.stream.Stream;

public record Picture(List<String> lines) {
    public Picture(Stream<String> lines) {
        this(lines.toList());
    }

    public int width() {
        return lines.stream().mapToInt(String::length).max().orElse(0);
    }
}
