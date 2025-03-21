package dev.manyroads.projects.asciimirror.example.view;


import dev.manyroads.projects.asciimirror.example.model.FilePath;

public class FilePathView extends View<FilePath> {
    @Override
    public void print(FilePath printable) {
        System.out.println(printable.path());
    }
}
