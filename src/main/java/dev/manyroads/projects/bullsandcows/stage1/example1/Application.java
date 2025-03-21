package dev.manyroads.projects.bullsandcows.stage1.example1;

import java.util.Random;
import java.util.Scanner;

public class Application {
    private final Code code = new Code("9305");
    private final Scanner sc = new Scanner(System.in);

    public void execute() {
        Grade grade = new Grade();
        System.out.println("The secret code is prepared: ****.\n");
        int count = 1;
        while (!grade.isSolved() && count < 10) {
            System.out.println("Turn " + count + ". Answer:");
            grade = code.check(new Code(generateRandom()));
            System.out.println(grade + "\n");
            count++;
        }
        if (grade.isSolved())
            System.out.println("Congrats!");
        System.out.println("The secret code is " + code + ".");
    }

    private String generateRandom() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int a = random.nextInt(10);
            code.append(a);
        }
        System.out.println(code.toString());
        return code.toString();
    }
}
