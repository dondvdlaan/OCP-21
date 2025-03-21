package dev.manyroads.streams;

import lombok.SneakyThrows;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.Locale;

public class StreamsClass {

    StringBuilder sb = new StringBuilder();
    // Send all output to the Appendable object sb
    Formatter formatter = new Formatter(sb, Locale.GERMANY);

    @SneakyThrows
    IntStream testingIntegerStream() {

        return new Random().ints(1000);
    }

    public static void main(String[] args) {
        StreamsClass streamsClass = new StreamsClass();
        streamsClass
                .testingIntegerStream()
                .forEach(n -> System.out.format("n:  %,d\n", n));
    }
}
