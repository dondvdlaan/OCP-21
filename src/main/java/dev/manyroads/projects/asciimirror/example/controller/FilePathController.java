package dev.manyroads.projects.asciimirror.example.controller;

import java.io.File;
import dev.manyroads.projects.asciimirror.example.model.FilePath;
import dev.manyroads.projects.asciimirror.example.view.FilePathView;

public class FilePathController extends Controller<FilePath> {
    public FilePathController(FilePath model, FilePathView view) {
        super(model, view);
    }

    public File getFile() {
        return new File(model.path());
    }
}