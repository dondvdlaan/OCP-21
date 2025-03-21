package dev.manyroads.projects.bullsandcows.stage1.example2;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static int calcCows(int code, int guess) {
        int count = 0;
        String c = Integer.toString(code);
        String g = Integer.toString(guess);
        for (int i = 0; i < c.length(); i++) {
            int idx = g.indexOf(c.charAt(i));
            if (idx >= 0 &&  g.charAt(idx) != c.charAt(idx)) {
                count++;
            }
        }
        return count;
    }

    static int calcBulls(int code, int guess) {
        int count = 0;
        String c = Integer.toString(code);
        String g = Integer.toString(guess);
        for (int i = 0; i < c.length(); i++) {
            int idx = g.indexOf(c.charAt(i));
            if (idx >= 0 &&  g.charAt(idx) == c.charAt(idx)) {
                count++;
            }
        }
        return count;
    }

    static void printGrade(int cows, int bulls) {
        //System.out.println(cows + " " + bulls);
        String sB = "";
        String sC = "";
        if (cows > 1) sC += "s";
        if (bulls > 1) sB += "s";
        if (cows == 0 && bulls == 0) {
            System.out.println("Grade: None.");
        } else if (cows == 0) {
            System.out.println("Grade: " + bulls + " bull" + sB + ".");
        } else if (bulls == 0) {
            System.out.println("Grade: " + cows + " cow" + sC + ".");
        } else {
            System.out.println("Grade: " + bulls + " bull" + sB + " and " + cows + "cow" + sC + ".");
        }
    }

    static void gameOn() {
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        String start = "The secret code is prepared: ****.";
        System.out.println(start);
        int code = random.nextInt(10000);
        int turn = 1;
        int guess;

        //System.out.println(code);

        do {
            System.out.println("\nTurn " + turn + ". Answer:");
            guess = sc.nextInt();
            turn++;
            printGrade(calcCows(code, guess), calcBulls(code, guess));
        } while (guess != code);

        System.out.println("Congrats! The secret code is " + code);

    }


    public static void main(String[] args) {
        //gameOn();

    }
}
