
package dev.manyroads.projects.trafficlightsimulator.stage2;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Objectives
 * In this stage, after the welcoming line, ask the users to input the desired number of roads and input the interval
 * at which the roads should open/close. After each request, read the value that a user provides.
 * <p>
 * Next, implement a looped selection menu. The loop (as well as the program execution) ends when a user selects 0 as
 * the desired option. Any other option (1, 2, 3) prints an informational text on the action performed (add, delete,
 * system) for each option.
 * Example
 * Welcome to the traffic management system!
 * Input the number of roads: > 5
 * Input the interval: > 3
 * Menu:
 * 1. Add road
 * 2. Delete road
 * 3. Open system
 * 0. Quit
 * > 1
 * Road added
 * Menu:
 * 1. Add road
 * 2. Delete road
 * 3. Open system
 * 0. Quit
 * > 2
 * Road deleted
 * Menu:
 * 1. Add road
 * 2. Delete road
 * 3. Open system
 * 0. Quit
 * > 3
 * System opened
 * Menu:
 * 1. Add road
 * 2. Delete road
 * 3. Open system
 * 0. Quit
 * > 0
 * Bye!
 */
public class Main {

    public static void main(String[] args) {
        new TrafficLight(new Scanner(System.in), new PrintConsole()).run();
    }
}

class TrafficLight implements Runnable {

    private  Scanner scanner;
    private  PrintConsole console;

    public TrafficLight(Scanner scanner, PrintConsole console) {
        this.scanner = scanner;
        this.console = console;
    }

     int getUserOption() {
        Supplier<Integer> userOption = () -> scanner.nextInt();
        return userOption.get();
    }

     void printMenu() {
        console.print(Constants.MENU_MSSG);
        Arrays.stream(Constants.MENU_OPTIONS).forEach(m-> console.print(m));
    }

    static String showInfoText(int userOption) {
        return switch (userOption) {
            case 1 -> Constants.ROAD_ADDED_MSSG;
            case 2 -> Constants.ROAD_DEL_MSSG;
            case 3 -> Constants.SYS_OPEN_MSSG;
            case 0 -> Constants.BYE_MSSG;
            default -> "no such option";
        };
    }

     void openingMenu() {
        console.print(Constants.WELCOME_MSSG);
        console.print(Constants.ROADS_MSSG);
        int roads = getUserOption();
        console.print(Constants.INTERVAL_MSSG);
        int interval = getUserOption();
        int userOption = -1;

        while (userOption != 0) {
            printMenu();
            userOption = getUserOption();
            console.print(showInfoText(userOption));
        }
    }

    @Override
    public void run() {
        openingMenu();
    }
}

class Constants {
    final static String WELCOME_MSSG = "Welcome to the traffic management system!";
    final static String ROADS_MSSG = "Input the number of roads: ";
    final static String INTERVAL_MSSG = "Input the interval: ";
    final static String MENU_MSSG = "Menu:";
    final static String ROAD_ADDED_MSSG = "Road added";
    final static String ROAD_DEL_MSSG = "Road deleted";
    final static String SYS_OPEN_MSSG = "System opened";
    final static String BYE_MSSG = "Bye!";
    final static String[] MENU_OPTIONS = new String[]{"1. Add", "2. Delete", "3. System", "0. Quit"};
}

@Slf4j
class PrintConsole {

    public void print(String message) {
        log.info(message);
    }
}
