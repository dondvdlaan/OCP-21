package dev.manyroads.files.filesnio2;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemTest {

    private static void createFileSystem() {
        FileSystem fileSystem = FileSystems.getDefault();
        System.out.println("getRootDirectories()");
        for (Path p : fileSystem.getRootDirectories()) System.out.println(p);
        System.out.println("getFileStores()");
        for (FileStore f : fileSystem.getFileStores()) System.out.println(f);
        System.out.println(fileSystem.getSeparator());
    }

    public static void main(String[] args) {
        createFileSystem();
    }
}
