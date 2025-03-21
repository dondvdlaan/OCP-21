package dev.manyroads.interfaces;

import static java.lang.Math.pow;

/**
 * The Circle class represents a circle.
 * Implement the Measurable interface and add a single method area that returns the area of a circle.
 * <p>
 * Note: Java has a built-in constant for PI: Math.PI
 * <p>
 * The class will be tested by creating an instance of Circle and invoking its area method:
 */
public class AreaOfACircle {
    public static void main(String[] args) {
        Measurable circle = new Circle(1);
        System.out.println(circle.area());
    }
}

class Circle implements Measurable {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * pow(radius, 2);
    }
}

interface Measurable {
    double area();
}
