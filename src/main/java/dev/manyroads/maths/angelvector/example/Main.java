package dev.manyroads.maths.angelvector.example;

import java.util.Scanner;

class Vec2D {
    int x;
    int y;

    public Vec2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getMagnitude() {
        return Math.hypot(x, y);
    }
}

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        Vec2D a = new Vec2D(scanner.nextInt(), scanner.nextInt());
        Vec2D b = new Vec2D(scanner.nextInt(), scanner.nextInt());
        int dotp = a.x * b.x + a.y * b.y;
        double scalarp = a.getMagnitude() * b.getMagnitude();
        double angleRad = Math.acos(dotp / scalarp);
        double angleDeg = Math.toDegrees(angleRad);
        System.out.println(angleDeg);
    }
}

