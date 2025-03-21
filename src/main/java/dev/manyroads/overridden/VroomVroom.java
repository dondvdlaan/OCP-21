package dev.manyroads.overridden;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Create a Vehicle hierarchy with a base Vehicle class and two subclasses: Car and Motorcycle.
 * Implement a method getInfo() in the Vehicle class that returns a string with vehicle type and speed.
 * Override getInfo() in subclasses to include additional information (number of doors for Car, has_sidecar for Motorcycle).
 * The program should take a single integer input representing the speed and output the getInfo() result for both a Car and
 * a Motorcycle instance with that speed.
 * Sample Input 1:
 * 60
 * Sample Output 1:
 * Car: Speed 60 mph, Doors: 4
 * Motorcycle: Speed 60 mph, Sidecar: false
 */
public class VroomVroom {
    public static void main(String[] args) {
        int speed = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            speed = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
        }
        Vehicle car = new Car(speed);
        Vehicle motorcycle = new Motorcycle(speed);
        System.out.println(car.getInfo());
        System.out.println(motorcycle.getInfo());
    }
}

class Vehicle {
    int speed;

    public Vehicle(int speed) {
        this.speed = speed;
    }

    public String getInfo() {
        return String.format("%s: Speed %d mph,",getClass().getSimpleName(),  speed);
    }
}

class Car extends Vehicle {
    int nrDoors;

    public Car(int speed) {
        super( speed);
        this.nrDoors = 4;
    }

    public String getInfo() {
        return String.format("%s Doors: %d", super.getInfo(), nrDoors);
    }
}

class Motorcycle extends Vehicle {
    boolean hasSideCar;

    public Motorcycle(int speed) {
        super( speed);;
        this.hasSideCar = false;
    }

    public String getInfo() {
        return String.format("%s Sidecar: %b", super.getInfo(), hasSideCar);
    }
}
