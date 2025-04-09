package dev.manyroads.projects.readabilityscore.stage3.example4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            ARI ari = new ARI();
            ari.runARI(reader, ari);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

