package dev.manyroads.functionalinterfaces.theory;

public class Main {

    @FunctionalInterface
    interface Func<T, R> {
        R apply(T val); // single abstract method
    }
// 3. Methode reference
    class Functions {

        public static long square3(long val) {
            return val * val;
        }
    }

    public static void main(String[] args) {

        // 1. Anonymous class
        Func<Long, Long> square = new Func<Long, Long>() {
            @Override
            public Long apply(Long val) {
                return val * val;
            }
        };

        long val = square.apply(10L);
        System.out.println(val);// the result is 100L

        // 2. Lambda
        Func<Long, Long> square2 = n -> n * 100;
        System.out.println(square2.apply(10L));

        Func<Long, Long> square3 = Functions::square3;
        System.out.println(square3.apply(99L));
    }
}