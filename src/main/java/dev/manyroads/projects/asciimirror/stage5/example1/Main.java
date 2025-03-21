package dev.manyroads.projects.asciimirror.stage5.example1;

import dev.manyroads.projects.asciimirror.stage5.example1.function.UserDefinedPath;
import dev.manyroads.projects.asciimirror.stage5.example1.model.MirroredPicture;
import dev.manyroads.projects.asciimirror.stage5.example1.model.Picture;

import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        var path = new UserDefinedPath(System.in).get();

        try (var lines = Files.lines(path)) {
            new MirroredPicture(new Picture(lines)).print();

        } catch (Throwable e) {
            System.out.println("File not found!");
        }

    }
}
