package dev.manyroads.projects.asciimirror.example.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileView extends View<File> {
    @Override
    public void print(File printable) throws FileNotFoundException {
        Scanner scanner = new Scanner(printable);
        while (scanner.hasNextLine())
            System.out.println(scanner.nextLine());
    }
}
