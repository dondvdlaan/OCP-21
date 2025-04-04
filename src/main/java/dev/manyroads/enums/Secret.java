package dev.manyroads.enums;

import java.util.Arrays;

/**
 * Write a program that counts and prints how many constants in the enumeration start with "STAR".
 * The enum is accessible during testing.
 *
 * // At least two constants start with STAR
 * enum Secret {
 *     STAR, CRASH, START, // ...
 * }
 */
public enum Secret {
    STAR, CRASH, START,GGHHJ,STARDOM,START_ME_UP;

    static void countAndPrintConstant(String prefix){
        System.out.println(Arrays.stream(Secret
                .values())
                .filter(e->e.name().startsWith(prefix))
                //.filter(e->e.name().substring(0,4).equals(prefix))
                .count());
    }
}

class Main{
    public static void main(String[] args) {
        Secret.countAndPrintConstant("STAR");
    }
}
