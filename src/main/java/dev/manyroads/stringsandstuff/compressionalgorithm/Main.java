package dev.manyroads.stringsandstuff.compressionalgorithm;

import java.util.Scanner;

public class Main {

    static void compression1(char[] inp) {
        StringBuilder output = new StringBuilder();
        int len = inp.length;
        for (int i = 0, count = 1; i < len; i++) {
            if (i < len - 1 && (inp[i] != inp[i + 1])) {
                output.append(Character.toString(inp[i]) + count);
                count = 1;
                continue;
            }
            if (i == len - 1) {
                output.append(Character.toString(inp[i]) + count);
            }
            count++;
        }
        System.out.println(output);
    }

    static void compression2(char[] inp) {
        StringBuilder output = new StringBuilder();
        int len = inp.length;
        for (int i = 0, count = 1; i < len; i++) {
            if (i < len - 1 && (inp[i] == inp[i + 1])) {
                count++;
            } else {
                output.append(Character.toString(inp[i]) + count);
                count = 1;
            }
        }
        System.out.println(output);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        char[] inp = scanner.nextLine().toCharArray();
//        compression2(inp);
        // separates all groups based on character
        String[] line = scanner.nextLine().split("(?<=(.))(?!\\1)");
        for(String s:line){
            System.out.println(s);
        }

    }
}
