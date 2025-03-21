package dev.manyroads.projects.trafficlightsimulator.stage2.example3;

import java.util.Scanner;

public class IOHandler {

    private Scanner scanner;


    public IOHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayWelcome() {
        System.out.println(Message.WELCOME_TEXT.getText());
    }

    public int promptMenu() {
        return readIntegerInputLN(Message.MENU_TEXT.getText());
    }

    public int promptNumberOfRoads() {
        return readIntegerInput(Message.NUMBER_OF_ROADS.getText());
    }

    public int promptNumberOfIntervals() {
        return readIntegerInput(Message.NUMBER_OF_INTERVALS.getText());
    }

    public void displayRoadAdded() {
        System.out.println(Message.ROAD_ADDED.getText());
    }

    public void displayRoadDeleted() {
        System.out.println(Message.ROAD_DELETED.getText());
    }

    public void displaySystemOpened(){
        System.out.println(Message.SYSTEM_OPENED.getText());
    }

    public void displayBye(){
        System.out.println(Message.BYE.getText());
    }

    public void displayEnterValidOption(){
        System.out.println(Message.ENTER_VALID_OPTION.getText());
    }

    public void displayActionDone(int option){
        if (option == 1){
            displayRoadAdded();
        }else if (option == 2){
            displayRoadDeleted();
        }else if(option == 3){
            displaySystemOpened();
        } else if (option == 0) {
            displayBye();
        }else {
            displayEnterValidOption();
        }
    }

    private int readIntegerInput(String message) {
        System.out.print(message);
        return scanner.nextInt();
    }
    private int readIntegerInputLN(String message) {
        System.out.println(message);
        return scanner.nextInt();
    }
}
