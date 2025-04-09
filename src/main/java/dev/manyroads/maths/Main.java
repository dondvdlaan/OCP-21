package dev.manyroads.maths;


import java.util.Scanner;

/**
 * Heron's formula
 * Many years ago when Paul went to school, he did not like the Heron's formula to calculate the area of a triangle,
 * because he considered it very complex. Once he decided to help all school students and write and distribute a program
 * calculating the area of a triangle by its three sides.
 * <p>
 * However, there was a problem: as Paul did not like the formula, he did not memorize it. Help him finish this act of
 * kindness and write the program calculating the area of a triangle with the known length of its sides, in accordance
 * with Heron's formula:
 */
public class Main {

    public static void main(String[] x) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        Shape triangle = new Triangle(a,b,c);
        System.out.println(triangle.getArea());
    }
}

abstract class Shape {
    abstract double getPerimeter();
    abstract double getArea();
}

class Triangle extends Shape {
    int a, b,  c;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    double getPerimeter() {
        return (a + b + c) / 2.0;
    }

    @Override
    double getArea() {
        double p = getPerimeter();
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}

