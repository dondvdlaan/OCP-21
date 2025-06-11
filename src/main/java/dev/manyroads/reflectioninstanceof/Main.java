package dev.manyroads.reflectioninstanceof;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        Shape[] shapes = {new Shape2D(), new Shape3D(), new Circle(), new Circle2(), new Cube2(), new Circle3()};
        int c = 0;
        for (Shape s : shapes) {
            if (s instanceof Shape2D shp) {
                if (shp.getClass() != Shape2D.class) ++c;
            }
        }
        System.out.println(c);
        System.out.println(Arrays
                .stream(shapes)
                .filter(s -> (s instanceof Shape2D) && (s.getClass() != Shape2D.class))
                .mapToInt(e -> 1)
                .reduce(0, (a, b) -> a + b));
        //.reduce(0, Integer::sum));

        // .count());
        System.out.println(Arrays.stream(shapes).filter(shape -> shape.getClass().getSuperclass() == Shape2D.class).count());
    }

}

class Shape {
}

class Shape2D extends Shape {
}

class Shape3D extends Shape {
}


class Circle extends Shape2D {
}

class Circle1 extends Shape2D {
}

class Circle2 extends Shape2D {
}

// ...  classes which extends Shape2D


class Cube extends Shape3D {
}

class Cube1 extends Shape3D {
}

class Cube2 extends Shape3D {
}

// ...  classes which extends Shape3D

class Circle3 extends Shape2D {
}
class ExtendsCircle extends Circle3 {
}