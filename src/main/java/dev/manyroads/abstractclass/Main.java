package dev.manyroads.abstractclass;

import java.util.Scanner;

abstract class Shape {
    protected double length;
    protected double width;
    protected double radius;

    Shape(double length, double width) {
        this.length = length;
        this.width = width;
    }

    Shape(double radius) {
        this.radius = radius;
    }

    abstract double area();
}


class Rectangle extends Shape {

    Rectangle(double length, double width){
        super(length, width);
    }

    @Override
    double area() {
        return length * width;
    }
}

class Circle extends Shape {

    public Circle(double radius) {
        super(radius);
    }

    @Override
    double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}

/**
 * Create an abstract class called Shape with an abstract method area().
 * Implement two classes Rectangle and Circle that extend the Shape class and implement the area() method.
 * Given the dimensions of a rectangle and a circle, calculate and print the area of each shape.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the dimensions of the rectangle
        double length = scanner.nextDouble();
        double width = scanner.nextDouble();

        // Read the radius of the circle
        double radius = scanner.nextDouble();

        Shape rectangle = new Rectangle(length,width);
        System.out.println(rectangle.area());
        Shape circle = new Circle(radius);
        System.out.println(circle.area());

        scanner.close();
    }
}
