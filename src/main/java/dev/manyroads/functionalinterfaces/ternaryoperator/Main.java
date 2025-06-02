package dev.manyroads.functionalinterfaces.ternaryoperator;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Implement a ternaryOperator method that accepts a predicate condition, and two functions ifTrue
 * and ifFalse and returns a function. The returning function takes an argument, and checks if the
 * condition predicate is true for this argument, and if it is — applies the ifTrue function to the
 * argument, otherwise, it applies ifFalse function.
 */
public class Main {
    static Predicate<Object> condition = Objects::isNull;
    static Function<Object, Integer> ifTrue = obj -> 0;
    static Function<CharSequence, Integer> ifFalse = CharSequence::length;
    static Function<String, Integer> safeStringLength = Operator.ternaryOperator(condition, ifTrue, ifFalse);


    public static void main(String[] args) {
        System.out.println(safeStringLength.apply("aaaaa"));
    }
}

class Operator {

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse){

        return s -> condition.test(s) ? ifTrue.apply(s) : ifFalse.apply(s);
    }
}
