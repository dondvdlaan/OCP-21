package dev.manyroads.projects.asciimirror.example;



import dev.manyroads.projects.asciimirror.example.controller.FileController;
import dev.manyroads.projects.asciimirror.example.controller.FilePathController;
import dev.manyroads.projects.asciimirror.example.model.FilePath;
import dev.manyroads.projects.asciimirror.example.view.FilePathView;
import dev.manyroads.projects.asciimirror.example.view.FileView;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Input the file path:");
        Scanner scanner = new Scanner(System.in);
        FilePath filePathModel = new FilePath(scanner.nextLine());
        FilePathView filePathView = new FilePathView();
        FilePathController filePathController = new FilePathController(
                filePathModel,
                filePathView);
        FileController fileController = new FileController(
                filePathController.getFile(),
                new FileView()
        );
        try {
            fileController.updateView();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found");
        }
    }
}
