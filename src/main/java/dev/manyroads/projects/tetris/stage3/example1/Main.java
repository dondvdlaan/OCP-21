package dev.manyroads.projects.tetris.stage3.example1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static String[][] field;
    public static Integer[][] piece;
    public static int view = 0;
    public static int width = 10;
    public static int height = 20;

    public static void move(int direction, boolean rotate) { // direction -1, 0, 1
        // rotate function
        if (rotate) view = (view + 1) % piece.length;
        // floor-right-left border check
        for (Integer cord: piece[view]) {
            if (cord >= (width - 1) * height) {
                if (rotate) view = (view - 1) % piece.length;
                return;
            }
            if (direction == 1 && cord % width == width - 1) direction = 0;
            if (direction == -1 && cord % width == 0) direction = 0;
        }
        // classic move
        for (Integer[] v : piece) {
            for (int i = 0; i < v.length; i++) {
                v[i] += width + direction;
            }
        }
    }
    public static void printArr(String[][]arr){
        Arrays.stream(arr).map(s -> String.join(" ", s) + "\n").forEach(System.out::print);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // map of elements
        HashMap<String, Integer[][]> pieceMap = new HashMap<>();
        pieceMap.put("O", new Integer[][] {{4, 14, 15, 5}});
        pieceMap.put("I", new Integer[][] {{4, 14, 24, 34}, {3, 4, 5, 6}});
        pieceMap.put("S", new Integer[][] {{5, 4, 14, 13}, {4, 14, 15, 25}});
        pieceMap.put("Z", new Integer[][] {{4, 5, 15, 16}, {5, 15, 14, 24}});
        pieceMap.put("L", new Integer[][] {{4, 14, 24, 25}, {5, 15, 14, 13}, {4, 5, 15, 25}, {6, 5, 4, 14}});
        pieceMap.put("J", new Integer[][] {{5, 15, 25, 24}, {15, 5, 4, 3}, {5, 4, 14, 24}, {4, 14, 15, 16}});
        pieceMap.put("T", new Integer[][] {{4, 14, 24, 15}, {4, 13, 14, 15}, {5, 15, 25, 14}, {4, 5, 6, 15}});

        //input parameters
        piece = pieceMap.get(sc.nextLine());
        width = sc.nextInt();
        height = sc.nextInt();
        field = new String[height][width];

        for (String[] b : field) Arrays.fill(b, "-");
        printArr(field);

        // main loop;
        while(true) {
            // draw the piece in position row|col
            for (int cord : piece[view]) field[(cord / width) ][cord % width] = "0"; //  String.valueOf(cord); //
            printArr(field);
            // make a move
            String userInput = sc.next();
            switch (userInput) {
                case "exit", "e" -> { return;}
                case "right", "r" -> move(1, false);
                case "left", "l" -> move(-1, false);
                case "down", "d" -> move(0, false);
                case "rotate", "q" -> move(0, true);
                default -> System.out.println("INVALID INPUT");
            }
            // clear all field with "-"
            for (String[] b : field) Arrays.fill(b, "-");
        }
    }
}
