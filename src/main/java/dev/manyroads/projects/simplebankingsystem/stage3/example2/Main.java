package dev.manyroads.projects.simplebankingsystem.stage3.example2;


public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        if (args[0].equals("-fileName") && args[1] != null) {
            controller.start(args[1]);
        } else {
            System.out.println("Database name is not supplied. \nExiting the program.");
        }
    }
}
