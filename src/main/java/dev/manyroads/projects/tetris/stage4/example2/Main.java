package dev.manyroads.projects.tetris.stage4.example2;

import java.util.*;

public class Main {
    public static HashMap<String, Integer[][]> pieceMap = new HashMap<>();
    public static String[][] field;
    public static ArrayList<Integer> dump;
    public static Integer[][] piece;
    public static int view = 0;
    public static int width;
    public static int height;
    public static boolean game;
    public static boolean showPiece = false;

    public static void move(int direction, boolean rotate) { // direction -1, 0, 1
        // rotate function
        if (rotate) view = (view + 1) % piece.length;
        // border and dump check
        for (Integer cord : piece[view]) {
            if (cord >= width * (height - 1) || dump.contains(cord + width)) {
                if (rotate) view = (view - 1) % piece.length;
                dump.addAll(Arrays.asList(piece[view]));
                showPiece = false;
                if (Collections.min(dump) < width) game = false; // end game condition
                return;
            }
            if (direction == 1 && cord % width == width - 1 || dump.contains(cord + 1)) direction = 0;
            if (direction == -1 && cord % width == 0 || dump.contains(cord - 1)) direction = 0;
        }
        // classic move
        for (Integer[] v : piece) {
            for (int i = 0; i < v.length; i++) {
                v[i] += width + direction;
            }
        }
    }

    public static void showField() {
        // clear all field with "-"
        for (String[] b : field) Arrays.fill(b, "-");
        // draw the piece in position row|col
        if (showPiece) {
            for (int cord : piece[view]) field[(cord / width)][cord % width] = "0";
        }
        // draw dump
        for (int cord : dump) field[(cord / width)][cord % width] = "0";
        printArr(field);
    }

    public static void newPiece(String input) {
        showPiece = true;
        view = 0;
        Integer[][] p = pieceMap.get(input);
        piece = new Integer[p.length][p[0].length];
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[0].length; j++) {
                piece[i][j] = p[i][j];
            }
        }
    }

    public static void disappear() {
        String[] fullRow = new String[width];
        Arrays.fill(fullRow, "0");

        for (int row = 0; row < height; row++) {
            if (Arrays.toString(field[row]).equals(Arrays.toString(fullRow))) {
                ArrayList<Integer> newDump = new ArrayList<>();
                for (Integer cord : dump) {
                    if (cord / (row * width) < 1) {
                        newDump.add(cord + width); // add lower value to newDump
                    }
                    if (cord / (row * width) >= 1 && cord % (width * row) >= width) {
                        newDump.add(cord); // add upper value to newDump
                    }
                }
                dump = newDump;
            }
        }
    }

    public static void printArr(String[][] arr) {
        Arrays.stream(arr)
                .map(s -> String.join(" ", s) + "\n")
                .forEach(System.out::print);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // add pieces to map of elements
        pieceMap.put("O", new Integer[][]{{4, 14, 15, 5}});
        pieceMap.put("I", new Integer[][]{{4, 14, 24, 34}, {3, 4, 5, 6}});
        pieceMap.put("S", new Integer[][]{{5, 4, 14, 13}, {4, 14, 15, 25}});
        pieceMap.put("Z", new Integer[][]{{4, 5, 15, 16}, {5, 15, 14, 24}});
        pieceMap.put("L", new Integer[][]{{4, 14, 24, 25}, {5, 15, 14, 13}, {4, 5, 15, 25}, {6, 5, 4, 14}});
        pieceMap.put("J", new Integer[][]{{5, 15, 25, 24}, {15, 5, 4, 3}, {5, 4, 14, 24}, {4, 14, 15, 16}});
        pieceMap.put("T", new Integer[][]{{4, 14, 24, 15}, {4, 13, 14, 15}, {5, 15, 25, 14}, {4, 5, 6, 15}});
        //input parameters
        width = sc.nextInt();
        height = sc.nextInt();
        field = new String[height][width];
        piece = new Integer[][]{};
        dump = new ArrayList<>();
        game = true;
        showField();

        main_loop:
        while (game) {
            String userInput = sc.next();
            switch (userInput) {
                case "exit", "e" -> {
                    return;
                }
                case "break", "b" -> {
                    disappear();
                    showField();
                }
                case "piece", "p" -> {
                    newPiece(sc.next());
                    showField();
                }
                case "right", "r" -> {
                    move(1, false);
                    showField();
                }
                case "left", "l" -> {
                    move(-1, false);
                    showField();
                }
                case "down", "d" -> {
                    move(0, false);
                    showField();
                }
                case "rotate", "q" -> {
                    move(0, true);
                    showField();
                }
                default -> System.out.println("INVALID INPUT");
            }
        }
        System.out.println("Game Over!");
    }
}
