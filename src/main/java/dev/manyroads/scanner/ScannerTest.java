package dev.manyroads.scanner;

import java.util.Scanner;

/*
Implement the method toPrimitive(). It should take a value of Boolean type and return a boolean.
If the passed value is null, the result should be false.
 */
public class ScannerTest {

    static boolean toPrimitive(Boolean aBoolean) {
        return aBoolean == null ? false : aBoolean;
    }

    public static int calculate(int i1, int i2) {
        return i1 * i2;
    }

    public static int calculate(int i1, int i2, int i3) {
        return (i1 + i2) * i3;
    }


    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String sInts = scanner.nextLine();
        String[] ints = sInts.split("\\s+");
        int nrInts = ints.length;
        switch (nrInts) {
            case 2 -> System.out.println(calculate(Integer.parseInt(ints[0]), Integer.parseInt(ints[1])));
            case 3 ->
                    System.out.println(calculate(Integer.parseInt(ints[0]), Integer.parseInt(ints[1]), Integer.parseInt(ints[2])));
            default -> System.out.println("Unknown type found");
        }
    }
}
