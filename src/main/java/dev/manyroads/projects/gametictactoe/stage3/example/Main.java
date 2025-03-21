package dev.manyroads.projects.gametictactoe.stage3.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter cells: ");
        char[] xo = sc.nextLine().toCharArray();
        char[][] field = new char[3][3];

        int xs = 0;
        int os = 0;
        Boolean xxx = false;
        Boolean ooo = false;

        // fill up the field, count X's & O's
        for (int i = 0; i < 9; i++) {
            field[i/3][i%3] = xo[i];

            if (xo[i] == 'X') {
                xs += 1;
            } else if (xo[i] == 'O') {
                os += 1;
            }
        }

        // check if row or column or any of diagonals is win for someone
        for (int i = 0; i < 3; i++) {
            int row = 0;
            int clm = 0;
            int mDiag = 0;
            int aDiag = 0;

            for (int j = 0; j < 3; j++) {
                row += field[i][j];
                clm += field[j][i];
                mDiag += field[j][j];
                aDiag += field[j][2-j];
            }

            // ASCII value for X is 88 (X+X+X is 264)
            // ASCII value for O is 79 (O+O+O is 237)
            xxx = xxx || row == 264 || clm == 264 || mDiag == 264 || aDiag == 264;
            ooo = ooo || row == 237 || clm == 237 || mDiag == 237 || aDiag == 237;
        }

        // print field and result
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.println(String.format("| %c %c %c |", field[i][0], field[i][1], field[i][2]));
        }
        System.out.println("---------");

        String result = Math.abs(xs-os) > 1 || xxx && ooo ? "Impossible"
                : xxx ? "X wins"
                : ooo ? "O wins"
                : xs + os == 9 ? "Draw"
                : "Game not finished";

        System.out.println(result);
    }
}
