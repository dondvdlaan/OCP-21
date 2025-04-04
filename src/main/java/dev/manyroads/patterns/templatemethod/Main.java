package dev.manyroads.patterns.templatemethod;

import java.util.Scanner;

/**
 * Build a house
 * There are three classes: an abstract class House, and two concrete classes, Wooden and Stone.
 * You must implement the abstract class House with a template method called build() to build a new house using the following algorithm:
 * <p>
 * Choose a location
 * Place a foundation
 * Place walls
 * Place windows
 * Place doors
 * Place roofs
 * Connect the house to the electrical grid
 * The Stone class is already provided. Use it to help you write the abstract methods in the abstract House class.
 * Make the Wooden class inherit from the House class and implement the methods according to the console output.
 * <p>
 * Sample Input 1:
 * stone
 * Sample Output 1:
 * <p>
 * Choose the best location for the new house
 * Place a stone foundation
 * Place stone walls
 * Place reinforced windows
 * Place reinforced doors
 * Place metal sheet roofs
 * Connect the house to the electrical grid
 * Sample Input 2:
 * <p>
 * wooden
 * Sample Output 2:
 * <p>
 * Choose the best location for the new house
 * Place a wooden foundation
 * Place wooden walls
 * Place wooden windows
 * Place wooden doors
 * Place metal sheet roofs
 * Connect the house to the electrical grid
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String client = scanner.next().toLowerCase();
        if ("stone".equals(client)) {
            House stone = new Stone();
            stone.build();
        } else if ("wooden".equals(client)) {
            House wooden = new Wooden();
            wooden.build();
        } else System.out.println("Error");
    }
}

abstract class House {

    void build() {
        chooseLocation();

        placeFoundation();
        placeWalls();
        placeWindows();
        placeDoors();
        placeRoofs();
        connectToGrid();
    }

    void chooseLocation() {
        System.out.println("Choose the best location for the new house");
    }

    abstract void placeFoundation();

    abstract void placeWalls();

    abstract void placeWindows();

    abstract void placeDoors();

    void placeRoofs() {
        System.out.println("Place metal sheet roofs");
    }

    void connectToGrid() {
        System.out.println("Connect the house to the electrical grid");
    }
}

class Stone extends House {

    @Override
    void placeFoundation() {
        System.out.println("Place a stone foundation");
    }

    @Override
    void placeWalls() {
        System.out.println("Place stone walls");
    }

    @Override
    void placeWindows() {
        System.out.println("Place reinforced windows");
    }

    @Override
    void placeDoors() {
        System.out.println("Place reinforced doors");
    }
}

class Wooden extends House {
    @Override
    void placeFoundation() {
        System.out.println("Place a wooden foundation");
    }

    @Override
    void placeWalls() {
        System.out.println("Place wooden walls");
    }

    @Override
    void placeWindows() {
        System.out.println("Place wooden windows");
    }

    @Override
    void placeDoors() {
        System.out.println("Place wooden doors");
    }
}
