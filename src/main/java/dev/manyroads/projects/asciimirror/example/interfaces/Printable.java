package dev.manyroads.projects.asciimirror.example.interfaces;

import java.io.FileNotFoundException;

/**
 * An interface that allows objects to be printable to the user
 * @param <T> Type of the object we want to print
 */
public interface Printable<T> {
    /**
     * Prints the printable parameter to the user
     * @param printable Object we want to print
     */
    void print(T printable) throws FileNotFoundException;
}
