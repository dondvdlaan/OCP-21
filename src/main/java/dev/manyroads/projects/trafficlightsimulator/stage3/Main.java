
package dev.manyroads.projects.trafficlightsimulator.stage3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Description
 * What if users didn't get enough sleep? All night they controlled the movement of imaginary roads and in the morning,
 * struggling with sleep and misclicks, enter the incorrect parameters. The system should handle wrong input and print
 * appropriate feedback.
 * In this stage, let's expand our program with error handling and some visual improvements.
 * The number of roads and intervals at which the roads should open/close should be positive integer values (note, that
 * 0 is not a positive value), so if a user provided any other input, our system should print an error that contains
 * the Incorrect input and Try again substrings.
 * The selected option in the menu should be either 0, 1, 2 or 3, so if a user made a mistake, our system should print the
 * Incorrect option feedback.
 * To make the output of our program more convenient, we can clear the previous output after each menu option is executed.
 * Due to the cross-platform nature of Java, clearing the console output can be complicated. You can use this snippet to
 * remove the console output.
 * Objectives
 * To complete this stage, your program must comply with the following requirements:
 * If the provided input for the number of roads or interval is not a positive integer value, the program should
 * print a line, containing Incorrect input and again substrings, followed by a new input.
 * If the chosen option is something other than 0, 1, 2, or 3, the program should output an Incorrect option feedback.
 * Modify the infinite loop so that when the result of option execution is shown, the program requires any
 * input before the next iteration.
 */
public class Main {

    public static void main(String[] args) {
        new TrafficLight(new Scanner(System.in)).run();
    }
}

class TrafficLight implements Runnable {

    private Scanner scanner;
    //private PrintConsole console;

    public TrafficLight(Scanner scanner) {
    //public TrafficLight(Scanner scanner, PrintConsole console) {
        this.scanner = scanner;
        //this.console = console;
    }

    int getUserOption() throws InputMismatchException {
        Supplier<Integer> userOption = () -> Integer.parseInt(scanner.nextLine());
        return userOption.get();
    }

    void printMenu() {
        System.out.println(Constants.MENU_MSSG);
        Arrays.stream(Constants.MENU_OPTIONS).forEach(System.out::println);
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

    int trafficLightInstallation() {
        int userOption = -1;
        boolean ok = false;
        while (!ok) {
            try {
                userOption = getUserOption();
                if (userOption <= 0) throw new RuntimeException();
                ok = true;
            } catch (Throwable ex) {
                System.out.println(Constants.INCORRECT_INP_ERR);
            }
        }
        return userOption;
    }

    void clearUserInput() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
        }
    }

    void openingMenu() {
        System.out.println(Constants.WELCOME_MSSG);
        System.out.print(Constants.ROADS_MSSG);
        int roads = trafficLightInstallation();
        System.out.print(Constants.INTERVAL_MSSG);
        int interval = trafficLightInstallation();

        printMenu();
        int userOption = -1;
        boolean ok = false;
        while (userOption != 0) {
            try {
                userOption = getUserOption();
                if (userOption < 0 || userOption > 3) throw new RuntimeException();
                ok = true;
                System.out.println(showInfoText(userOption));
            } catch (Throwable ex) {
                System.out.println(Constants.INCORRECT_OP_ERR);
                userOption = -1;
                //clearUserInput();
            }
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
    final static String INCORRECT_INP_ERR = "Error! Incorrect input. Try again!";
    final static String INCORRECT_OP_ERR = "Incorrect option";
}

//@Slf4j
//class PrintConsole {
//
//    public void print(String message) {
//        log.info(message);
//    }
//}
