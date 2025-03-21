package dev.manyroads.projects.trafficlightsimulator.stage2.example3;


import java.util.Scanner;

public class MainMenuController {

    private IOHandler ioHandler;

    public MainMenuController(){
        ioHandler = new IOHandler(new Scanner(System.in));
    }

    public void run(){
        ioHandler.displayWelcome();
        int numberOfRoads = ioHandler.promptNumberOfRoads();
        int numberOfIntervals = ioHandler.promptNumberOfIntervals();

        while(true){
            int option = ioHandler.promptMenu();
            if (!processOption(option)) {
                break;
            }
        }
    }

    private boolean processOption(int option) {
        switch (option) {
            case 1:
            case 2:
            case 3:
            case 0:
                ioHandler.displayActionDone(option);
                return option != 0;
            default:
                ioHandler.displayEnterValidOption();
                return true;
        }
    }

}
