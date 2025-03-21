package dev.manyroads.files.filesnio2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.stream.Stream;

public class Nio2MakeDir {

    private static void copyFiles() {
        Path basPath = Path.of("E:\\temp");
        Path dir = Path.of("E:\\temp\\Nio2Files");
        try (Stream<Path> paths = Files.list(dir)) {
            paths.forEach(p -> {
                try {
                    Files.copy(Path.of(dir + "\\" + p.getFileName()), Path.of(basPath + "\\" + p.getFileName()));
                } catch (IOException ex) {
                    System.out.println("stream foutje: " + ex.getMessage());
                }
            });
        } catch (IOException ex) {
            System.out.println("Copy fout: " + ex.getCause());
        }
    }

    private static void makeDirs() {
        Path basPath = Path.of("E:\\temp");
        // List files
        try (Stream<Path> files = Files.list(basPath)) {
            files.forEach(f -> System.out.println("File: " + f.getFileName()));
        } catch (IOException ex) {
            System.out.println("Problems w files: " + ex.getMessage());
        }
        // Create dir
        try (Stream<Path> paths = Files.list(basPath)) {
            Path targetDir = Files.createDirectory(Path.of(basPath + "\\" + "Nio2Files"));
            System.out.println("targetDir: " + targetDir);
            paths.forEach(p -> {
                try {
                    Path fPath = Path.of(basPath + "\\" + p.getFileName());
                    System.out.println("fPath: " + fPath);
                    Files.move(fPath, Path.of(targetDir + "\\" + p.getFileName()));
                } catch (IOException e) {
                    System.out.println("Problems w move: " + e.getMessage());
                }
            });
        } catch (IOException ex) {
            System.out.println("Problems w dir: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        //makeDirs();
        copyFiles();
    }
}
