package dev.manyroads.superstuff;

import java.util.Scanner;

/**
 * Inheriting shapes with constructors
 * Create a base class called Shape with a constructor that takes the shape's name as a parameter. Then create a derived class
 * called Rectangle that inherits from Shape and has a constructor that takes the shape's name, width, and height as parameters.
 * The Rectangle constructor should call the base class constructor using the super keyword. Finally,
 * create a Rectangle object and print out its name, width, and height.
 */
public class Shape {
    String name;

    public Shape(String name){
        this.name = name;
    }
}
class Rectangle extends Shape{
    double width;
    double height;

    public  Rectangle(String name, double width,double height){
        super(name);
        this.width=width;
        this.height= height;
    }

    @Override
    public String toString() {
        return
                "Name: " + name + "\n"+
                "Width: " + width + "\n"+
                "Height: " + height;
    }
}
class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        Rectangle rectangle = new Rectangle(name,width,height);
        System.out.println(rectangle);
    }
}
