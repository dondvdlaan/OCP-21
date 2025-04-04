package dev.manyroads.projects.searchengine.stage3;

import java.lang.String;

enum Messages {
    NR_PEOPLE("Enter the number of people:"),
    ENTER_PEOPLE("Enter all people:"),
    MAIN_MENU("""
            1. Search information.
            2. Print all data.
            0. Exit."""),
    WRONG_OPTION("Incorrect option! Try again."),
    NR_QUERIES("Enter the number of search queries:"),
    SEARCH_QUERY("Enter a name or email to search all suitable people."),
    LIST_PEOPLE("=== List of people ==="),
    FOUND_PEOPLE("Found people:"),
    NO_MATCH_PEOPLE("No matching people found.");

    String description;

    Messages(String description) {
        this.description = description;
    }
}
