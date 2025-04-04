package dev.manyroads.projects.searchengine.stage2.example1;

public class SearchException extends RuntimeException {
    public SearchException(String message) {
        super(message);
    }

    public SearchException() {
        this("No matching people found.");
    }
}
