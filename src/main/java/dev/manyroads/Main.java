package dev.manyroads;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("file.txt"); // some file

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.printf("%s dolor sit %s", "Lorem", "ipsum", "amet");
        }
    }
}




