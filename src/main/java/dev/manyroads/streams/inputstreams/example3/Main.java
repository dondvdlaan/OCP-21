package dev.manyroads.streams.inputstreams.example3;

// Posted from EduTools plugin
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Stack;

class Main {
    public static void main(String[] args) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int data = reader.read();
            Stack<Character> charStack = new Stack<>();

            while (data != -1) {
                charStack.push((char) data);
                data = reader.read();
            }
            while (!charStack.empty()) {
                System.out.print(charStack.pop());
            }
        }
    }
}
