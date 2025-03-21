package dev.manyroads.projects.asciimirror.example.controller;

import dev.manyroads.projects.asciimirror.example.view.View;

import java.io.FileNotFoundException;

/**
 * Abstract class for building a controller
 * @param <T> Model object we want to build a controller for
 */
public abstract class Controller<T> {
    /**
     * Model of the controller
     */
    protected final T model;

    /**
     * View of the model
     */
    protected final View view;

    protected Controller(T model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Prints the view to the user
     */
    public void updateView() throws FileNotFoundException {
        view.print(model);
    }
}
