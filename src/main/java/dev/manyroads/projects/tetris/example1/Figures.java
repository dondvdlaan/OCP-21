package dev.manyroads.projects.tetris.example1;

public class Figures {
    Integer[][] O = {{5, 6, 9, 10}};
    Integer[][] I = {{1, 5, 9, 13}, {4, 5, 6, 7}, {1, 5, 9, 13}, {4, 5, 6, 7}};
    Integer[][] S = {{6, 5, 9, 8}, {5, 9, 10, 14}, {6, 5, 9, 8}, {5, 9, 10, 14}};
    Integer[][] Z = {{4, 5, 9, 10}, {2, 5, 6, 9}, {4, 5, 9, 10}, {2, 5, 6, 9}};
    Integer[][] L = {{1, 5, 9, 10}, {2, 4, 5, 6}, {1, 2, 6, 10}, {4, 5, 6, 8}};
    Integer[][] J = {{2, 6, 9, 10}, {4, 5, 6, 10}, {1, 2, 5, 9}, {0, 4, 5, 6}};
    Integer[][] T = {{1, 4, 5, 6}, {1, 4, 5, 9}, {4, 5, 6, 9}, {1, 5, 6, 9}};

    String[] matrix = {"-", "-", "-", "-",
            "-", "-", "-", "-",
            "-", "-", "-", "-",
            "-", "-", "-", "-"};

    public Integer[][] getFigure(String figure) {
        switch (figure) {
            case "O":
                return O;
            case "I":
                return I;
            case "S":
                return S;
            case "Z":
                return Z;
            case "L":
                return L;
            case "J":
                return J;
            case "T":
                return T;
            default:
                return null;
        }
    }


    public static void displayFigure(Integer[][] figure, int rotation) {
        for (int i = 0; i <16; i++) {
            if (i % 4 == 0) {
                System.out.println();
            }
            if (figure[rotation][0] == i || figure[rotation][1] == i || figure[rotation][2] == i || figure[rotation][3] == i) {
                System.out.print("0 ");
            } else {
                System.out.print("- ");
            }
        }
        System.out.println();
    }

}