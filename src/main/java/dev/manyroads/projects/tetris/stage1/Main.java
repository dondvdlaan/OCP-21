package dev.manyroads.projects.tetris.stage1;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Tetris().start();
    }
}

class Tetris extends Pieces {

    void start() {
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();
        printTetris(blank, 1);
        Integer[][] piece = switch (selection) {
            case "O" -> O;
            case "I" -> I;
            case "S" -> S;
            case "Z" -> Z;
            case "L" -> L;
            case "J" -> J;
            case "T" -> T;
            default -> throw new IllegalArgumentException("Invalid piece type");
        };
        printTetris(piece, 4);
        if (piece != O) printTetris(T, 1);
    }

    void printTetris(Integer[][] piece, int rotations) {
        for (int rotation = 0; rotation < rotations; rotation++) {
            System.out.println();
            int pos = 0;
            for (String[] sOuter : board) {
                for (String sInner : sOuter) {
                    if (pos == piece[rotation][0] || pos == piece[rotation][1] || pos == piece[rotation][2] || pos == piece[rotation][3]) {
                        sInner = "0";
                    }
                    System.out.print(sInner + " ");
                    pos++;
                }
                System.out.println();
            }
        }
    }

    // Alternative to print board
    public static void printArr(String[][] arr) {
        Arrays.stream(arr)
                .map(s -> String.join(" ", s) + "\n")
                .forEach(System.out::print);
        System.out.println();
    }

    // Alternative 2 to print board
    static String renderRotation(Integer[] rotation) {
        String[] buf = new String[16];
        String ending;
        for (int i = 0; i < buf.length; i++) {
            if (i % 4 == 3) ending = "\n";
            else ending = " ";
            buf[i] = "-" + ending;
        }
        for(int index: rotation){
            buf[index]=buf[index].replace("-","0");
        }
        return String.join("", buf);
    }

    public static void main(String[] args) {
        Integer[] iArr ={5, 6, 9, 10};
        System.out.println(renderRotation(iArr));
    }

}

abstract class Pieces {
    String[][] board = {
            {"-", "-", "-", "-"},
            {"-", "-", "-", "-"},
            {"-", "-", "-", "-"},
            {"-", "-", "-", "-"}
    };
    Integer[][] blank = {{-1, -1, -1, -1}};

    Integer[][] O = {{5, 6, 9, 10}};
    Integer[][] I = {{1, 5, 9, 13}, {4, 5, 6, 7}, {1, 5, 9, 13}, {4, 5, 6, 7}};
    Integer[][] S = {{6, 5, 9, 8}, {5, 9, 10, 14}, {6, 5, 9, 8}, {5, 9, 10, 14}};
    Integer[][] Z = {{4, 5, 9, 10}, {2, 5, 6, 9}, {4, 5, 9, 10}, {2, 5, 6, 9}};
    Integer[][] L = {{1, 5, 9, 10}, {2, 4, 5, 6}, {1, 2, 6, 10}, {4, 5, 6, 8}};
    Integer[][] J = {{2, 6, 9, 10}, {4, 5, 6, 10}, {1, 2, 5, 9}, {0, 4, 5, 6}};
    Integer[][] T = {{1, 4, 5, 6}, {1, 4, 5, 9}, {4, 5, 6, 9}, {1, 5, 6, 9}};
}
