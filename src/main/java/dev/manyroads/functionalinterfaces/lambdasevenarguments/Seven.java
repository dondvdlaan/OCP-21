package dev.manyroads.functionalinterfaces.lambdasevenarguments;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Write a lambda expression that accepts seven (!) string arguments and returns a string in
 * uppercase concatenated from all of them (in the order of arguments).
 */
public class Seven {

    @FunctionalInterface
    interface SeptenaryStringFunction<T, U, V, W, X, Y, Z, R> {
        R apply(T val1, U val2, V val3, W val4, X val5, Y val6, Z val7);
    }

    public static SeptenaryStringFunction<String, String, String, String, String, String, String, String> fun =
            (t, u, v, w, x, y, z) ->
                    new StringBuilder()
                            .append(t)
                            .append(u)
                            .append(v)
                            .append(w)
                            .append(x)
                            .append(y)
                            .append(z)
                            .toString()
                            .toUpperCase();
    public static SeptenaryStringFunction<String, String, String, String, String, String, String, String> fun2 =
            (t, u, v, w, x, y, z) ->
                    Stream.of(t, u, v, w, x, y, z)
                            .map(String::toUpperCase)
                            .collect(Collectors.joining());
    public static SeptenaryStringFunction<String, String, String, String, String, String, String, String> fun3 =
            (t, u, v, w, x, y, z) -> {
                var sb = new StringBuilder();
                Stream.of(t, u, v, w, x, y, z).forEach(sb::append);
                return sb.toString().toUpperCase();
            };

    public static void main(String[] args) {

        System.out.println(fun3.apply("a", "b", "c", "d", "e", "f", "g"));
    }
}
