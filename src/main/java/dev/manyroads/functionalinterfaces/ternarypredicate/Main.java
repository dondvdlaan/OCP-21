package dev.manyroads.functionalinterfaces.ternarypredicate;

import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

/**
 * Write your own functional interface called TernaryIntPredicate and use it with a lambda expression.
 * The interface must have a single non-static (and non-default) method called test that accepts three int
 * arguments and returns a boolean value.
 * <p>
 * Also, write a lambda expression of the TernaryIntPredicate type with three int arguments.
 * The lambda expression should return true if all passed values are different, otherwise it should return false.
 * The expression should be assigned to a static field named ALL_DIFFERENT.
 */
public class Main {

    @FunctionalInterface
    static interface TernaryIntPredicate<T, R> {
        R test(T int1, T int2, T int3);
    }

    // alternative 1
    //@FunctionalInterface
//    public interface TernaryIntPredicate {
//        // Write a method here
//        boolean test(int java, int sucks, int ass);
//    }

    // alternative 2
//    public static final TernaryIntPredicate ALL_DIFFERENT = numbers ->
//            IntStream.of(numbers).distinct().count() == numbers.length;
//
//    @FunctionalInterface
//    public interface TernaryIntPredicate {
//        boolean test(int... numbers);
//    }

    // alternative 3
//    public static final TernaryIntPredicate ALL_DIFFERENT = (int... v) -> Arrays.equals(Arrays.stream(v)
//            .distinct()
//            .toArray(), v);
//
//    @FunctionalInterface
//    public interface TernaryIntPredicate {
//        boolean test(int... i);
//    }

    static TernaryIntPredicate<Integer, Boolean> ALL_DIFFERENT = (a, b, c) ->
            !Objects.equals(a, b) && !Objects.equals(b, c) && !Objects.equals(a, c);

    public static void main(String[] args) {
        System.out.println(ALL_DIFFERENT.test(1,2,1));
    }
}
