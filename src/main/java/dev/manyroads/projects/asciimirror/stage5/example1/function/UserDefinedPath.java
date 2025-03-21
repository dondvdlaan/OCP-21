package dev.manyroads.projects.asciimirror.stage5.example1.function;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.function.Supplier;

public record UserDefinedPath(InputStream source) implements Supplier<Path> {

    @Override
    public Path get() {
        var scanner = new Scanner(source);
        System.out.println("Input the file path:");
        return Path.of(scanner.nextLine());
    }
}
