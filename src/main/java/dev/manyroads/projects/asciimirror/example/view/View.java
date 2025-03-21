package dev.manyroads.projects.asciimirror.example.view;


import dev.manyroads.projects.asciimirror.example.interfaces.Printable;

/**
 * Abstract class to build a printable view
 * @param <T> Model of the view
 */
public abstract class View<T> implements Printable<T> { }