package dev.manyroads.streams.inputstreams.example1;

// Posted from EduTools plugin
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            reader.lines()
                    .map(StringBuilder::new)
                    .map(StringBuilder::reverse)
                    .forEach(System.out::println);
        }
    }
}
