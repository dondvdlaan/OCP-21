package dev.manyroads.equals;


import java.util.Arrays;
import java.util.Objects;

public class EqualsTest {
    void checkInstance() {
        var a = new StringBuilder("Holita");
        var b = new StringBuilder(" Holita".trim());
        var c = "ddddd";
        System.out.println(a.equals(b));                        // false; checks references
        System.out.println(a.toString().equals(b.toString()));  // true; check logical equality

    }

    void checkIStrings() {
        var a = new String("Holita");
        var b = new String(" Holita".trim());
        var c = "ddddd";
        System.out.println(a.equals(b));                // true; checks logical equality
        System.out.println(a == b);                     // false; checks references
        System.out.println(Objects.equals(a, b));       // true; checks logical equality
    }

    void checkArrays() {
        int[] ints = {1, 2, 3};
        int[] intsII = {1, 2, 3};
        System.out.println(ints.equals(intsII));        // false, only check refs
        System.out.println(Arrays.equals(ints,intsII)); // true; checks logical equality
    }

    public static void main(String[] args) {
        // new TestFloat().checkInstance();
        //new TestFloat().checkIStrings();
        new EqualsTest().checkArrays();
    }
}

