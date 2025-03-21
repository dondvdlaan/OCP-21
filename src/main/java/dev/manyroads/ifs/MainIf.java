package dev.manyroads.ifs;

import java.util.Scanner;

public class MainIf {
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
            int n = scanner.nextInt();
            if(n > 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
