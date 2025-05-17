package dev.manyroads;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(!(false && true) || false);
    }

    static Level getLevel(String selection) {
        return switch (selection) {
            case "severe" -> Level.SEVERE;
            case "warning" -> Level.WARNING;
            case "info" -> Level.INFO;
            case "config" -> Level.CONFIG;
            case "fine" -> Level.FINE;
            case "finer" -> Level.FINER;
            case "finest" -> Level.FINEST;
            default -> null;
        };
    }
}