package dev.manyroads.functionalinterfaces.lambdasevenarguments.example1;

public class Main {
}
class Seven {
    public static SeptenaryStringFunction fun = Seven::conc;

    public static String conc(String... str) {
        return String.join("", str).toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(fun.apply("a", "b", "c", "d", "e", "f", "g"));

    }
}

@FunctionalInterface
interface SeptenaryStringFunction {
    String apply(String s1, String s2, String s3, String s4, String s5, String s6, String s7);
}

