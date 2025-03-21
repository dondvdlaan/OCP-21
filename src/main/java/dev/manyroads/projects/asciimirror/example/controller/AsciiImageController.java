package dev.manyroads.projects.asciimirror.example.controller;


import dev.manyroads.projects.asciimirror.example.model.AsciiImage;
import dev.manyroads.projects.asciimirror.example.view.AsciiImageView;

public class AsciiImageController extends Controller<AsciiImage> {

    public AsciiImageController(AsciiImage model, AsciiImageView view) {
        super(model, view);
    }
}
