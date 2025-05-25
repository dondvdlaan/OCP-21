package dev.manyroads;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
      try(Scanner scanner = new Scanner(System.in)){
          double first=scanner.nextDouble();
          double second=scanner.nextDouble();
          double res =first-second;
          System.out.println(-res);
      }
    }


}

