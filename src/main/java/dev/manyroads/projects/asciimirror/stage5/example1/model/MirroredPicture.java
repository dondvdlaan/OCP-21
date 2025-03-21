package dev.manyroads.projects.asciimirror.stage5.example1.model;


import dev.manyroads.projects.asciimirror.stage5.example1.function.FixedWidth;
import dev.manyroads.projects.asciimirror.stage5.example1.function.MirroredLine;

public record MirroredPicture(Picture picture) {
    public void print() {
        picture.lines().stream()
                .map(new FixedWidth(picture.width()))
                .map(new MirroredLine())
                .forEach(System.out::println);
    }
}
