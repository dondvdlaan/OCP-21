package dev.manyroads;


import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            final int[] ints = IntStream.generate(sc::nextInt).limit(6).toArray();
            LocalTime time1 = LocalTime.of(ints[0],ints[1],ints[2]);
            LocalTime time2 = LocalTime.of(ints[3],ints[4],ints[5]);
            System.out.println(Duration.between(time1,time2).toSeconds());
        }
    }
}




