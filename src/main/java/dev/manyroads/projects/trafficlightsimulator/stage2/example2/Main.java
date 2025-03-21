package dev.manyroads.projects.trafficlightsimulator.stage2.example2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new MainMenuController(new ConsolePrinter(), new Scanner(System.in)).run();
    }
}
