package dev.manyroads.projects.tetris.example2;

import java.util.HashMap;
import java.util.Scanner;

public class UI {
    private static final Piece I = new I();

    public static void start() {
        HashMap<String, Piece> pieces = new HashMap<>();
        pieces.put("O", new O());
        pieces.put("I", new I());
        pieces.put("S", new S());
        pieces.put("Z", new Z());
        pieces.put("L", new L());
        pieces.put("J", new J());
        pieces.put("T", new T());

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();
        pieces.get(input).rotateFull();
    }
}
