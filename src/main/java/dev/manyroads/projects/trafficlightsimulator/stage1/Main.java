package dev.manyroads.projects.trafficlightsimulator.stage1;

/**
 * Objectives
 * As a start, develop a simple program that prints six non-empty lines to the output.
 * <p>
 * Being a very polite program, it greets users on the first line with the Welcome substring and tells them that
 * they've just started traffic management system.
 * The following line is the list's title, with Menu substring.
 * After that, finally, display the list line-by-line in exact order, indexing and substrings:
 * Example 1:
 * Welcome to the traffic management system!
 * Menu:
 * 1. Add
 * 2. Delete
 * 3. System
 * 0. Quit
 */
public class Main {

    static void openingMenu() {
        final String WELCOME_MSSG = "Welcome to the traffic management system!";
        final String MENU_MSSG = "Menu:";
        final String[] MENU_OPTIONS = new String[]{"1. Add", "2. Delete", "3. System", "0. Quit"};

        System.out.println(WELCOME_MSSG);
        System.out.println(MENU_MSSG);
        for (String mOption : MENU_OPTIONS) {
            System.out.println(mOption);
        }

    }

    public static void main(String[] args) {
        openingMenu();
    }
}
