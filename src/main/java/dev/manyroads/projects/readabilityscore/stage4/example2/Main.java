package dev.manyroads.projects.readabilityscore.stage4.example2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try {
            new Application(
                    new TextStatistics(
                            Files.readString(
                                    Path.of(args[0])))
            ).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
