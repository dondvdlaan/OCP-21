package dev.manyroads.projects.asciimirror.example.controller;


import dev.manyroads.projects.asciimirror.example.view.View;

import java.io.File;

public class FileController extends Controller<File> {
    public FileController(File model, View view) {
        super(model, view);
    }
}
