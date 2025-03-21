package dev.manyroads.projects.asciimirror.example.view;


import dev.manyroads.projects.asciimirror.example.model.AsciiImage;

public class AsciiImageView extends View<AsciiImage> {
    @Override
    public void print(AsciiImage printable) {
        System.out.print(printable.image());
    }
}
