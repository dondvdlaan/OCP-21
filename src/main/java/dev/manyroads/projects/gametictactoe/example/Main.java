package dev.manyroads.projects.gametictactoe.example;

public class Main {
    int i;
    public static void main(String[] args) {
        Field.getInstance().fillRandom();
        System.out.println(Field.getInstance().toString());
    }

}