package dev.manyroads.maths.angelvector;

import java.util.Arrays;
import java.util.Scanner;

/**
 * The angle between vectors
 * You are given two 2D vectors. Find the angle (in degrees) between them.
 * If you don't know how to find the angle, see here: http://www.wikihow.com/Find-the-Angle-Between-Two-Vectors.
 * <p>
 * Input data format
 * The first line contains two components of the first vector; the second line contains two components of the second vector.
 * Components in one line are separated by space.
 * <p>
 * Output data format
 * One double value: an angle between two vectors. The result can have an error of less than 1e-8.
 * <p>
 * Sample Input 1:
 * 1 3
 * 4 2
 * Sample Output 1:
 * 45
 * Sample Input 2:
 * 0 4
 * 0 4
 * Sample Output 2:
 * 0
 */
public class Main {
    /*
    Dot Product Formula was chosen. Alternative methosd is Cross Product Formula (not implemented)
     */
    public static void main(String[] args) {
        int[] coord;
        Scanner scanner = new Scanner(System.in);
        coord = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Vec2D u = new Vec2D(coord[0], coord[1]);
        coord = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Vec2D v = new Vec2D(coord[0], coord[1]);

        VectorContext dotProduct = new VectorContext(new DotProduct(u, v));
        System.out.printf("%.0f", dotProduct.getAngle());
    }
}

interface AngleBetweenVectors {
    double getAngleBetweenVectors();
}

class VectorContext {

    AngleBetweenVectors angleBetweenVectors;

    public VectorContext(AngleBetweenVectors angleBetweenVectors) {
        this.angleBetweenVectors = angleBetweenVectors;
    }

    double getAngle() {
        return angleBetweenVectors.getAngleBetweenVectors();
    }
}

class DotProduct implements AngleBetweenVectors {

    Vec2D u;
    Vec2D v;

    public DotProduct(Vec2D u, Vec2D v) {
        this.u = u;
        this.v = v;
    }

    public double getAngleBetweenVectors() {
        double magU = u.getManitudeVector();
        double magV = v.getManitudeVector();
        double dotProd = getDotProductVectors(u, v);
        double cosAngle = getCosAngle(dotProd, magU, magV);
        double angleRad = Math.acos(cosAngle);
        return Math.toDegrees(angleRad);
    }

    private double getCosAngle(double dotProd, double magU, double magV) {
        return dotProd / (magU * magV);
    }

    private double getDotProductVectors(Vec2D u, Vec2D v) {
        return u.x() * v.x() + u.y() * v.y();    }
}

record Vec2D(int x, int y) {

    public double getManitudeVector() {
        return Math.hypot(x, y);
    }
}
