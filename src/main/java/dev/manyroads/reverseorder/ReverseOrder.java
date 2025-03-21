package dev.manyroads.reverseorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseOrder {

    private final List<String> lijstStrings;

    public ReverseOrder(List<String> lijstStrings) {
        this.lijstStrings = lijstStrings;
    }

    public List<String> reverseOrderI() {
        List<String> copyLijstStrings = new ArrayList<>(this.lijstStrings);
        Collections.reverse(copyLijstStrings);
        return copyLijstStrings;
    }

    public List<String> reverseOrderII() {
        return new ArrayList<>(this.lijstStrings.reversed());
    }

    StringBuilder reverseString(StringBuilder stringetjs) {
        return stringetjs.reverse();
    }

    public static void main(String[] args) {
        ReverseOrder reverseOrder = new ReverseOrder(new ArrayList<String>(
                Arrays.asList("Geeks", "for", "Guuks", "aap", "noot", "mies")));
        /*
        System.out.println("reverseOrderI()");
        System.out.println(reverseOrder.lijstStrings);
        System.out.println(reverseOrder.reverseOrderI());

        System.out.println("reverseOrderII()");
        System.out.println(reverseOrder.lijstStrings);
        System.out.println(reverseOrder.reverseOrderII());

         */

        System.out.println("reverseString");
        var sb = new StringBuilder("ABC");
        System.out.println(reverseOrder.reverseString(sb));
        System.out.println("sb: " + sb);
    }
}
