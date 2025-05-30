package dev.manyroads.patterns.factories.motorstaticfactory;

import java.util.Scanner;

/**
 * Motor static factory
 * In the very heart of suburbia, there stood a motor factory; in the very heart of that factory worked a
 * programmer. Implement the static method make of the MotorStaticFactory that produces motors of different
 * types. The method takes three parameters: the type of a motor as a character, model as a string, and power
 * as a long number. It should return a new motor according to the type with initialized fields.
 * <p>
 * Here is the correspondence between the passed type and the class of the motor: 'P' for pneumatic, 'H'
 * for hydraulic, 'E' for electric and 'W' for warp. Ignore the upper/lower case when creating motors,
 * i.e. 'p' must work as well as 'P'. If an invalid character is given, the method should return null.
 * <p>
 * Do not change the provided code of the motor classes.
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            char motor = sc.nextLine().toUpperCase().charAt(0);
            String model = sc.nextLine();
            long pwr = sc.nextLong();
            Motor motor1 = MotorStaticFactory.make(motor, model, pwr);
            if(motor1!=null) System.out.println(motor1.getClass().getName()+" "+motor1.motor+" "+motor1.power);
        }
    }
}

class MotorStaticFactory {

    static Motor make(char type, String model, long power) {
        return switch (Character.toUpperCase(type)) {
//            case 'P','p' -> new PneumaticMotor(model, power);
//            case 'H','h' -> new HydraulicMotor(model, power);
//            case 'E','e' -> new ElectricMotor(model, power);
//            case 'W','w' -> new PneumaticMotor(model, power);
            default -> null;
        };
    }
}

abstract class Motor {
    char motor;
    String model;
    long power;

    abstract Motor produce(char motor, String model, long power);

    public String toString() {
        return new StringBuilder()
                .append("Motor: " + motor + "\n")
                .append("Motor: " + model + "\n")
                .append("Power: " + power + "\n")
                .toString();
    }
}

class PneumaticMotor extends Motor {

    @Override
    public Motor produce(char motor, String model, long power) {
        super.motor = motor;
        super.model = model;
        super.power = power;
        return  this;
    }
}

class HydraulicMotor extends Motor {

    @Override
    public Motor produce(char motor, String model, long power) {
        super.motor = motor;
        super.model = model;
        super.power = power;
        return  this;
    }
}

class ElectricMotor extends Motor {

    @Override
    public Motor produce(char motor, String model, long power) {
        super.motor = motor;
        super.model = model;
        super.power = power;
        return  this;
    }
}

class WarpMotor extends Motor {

    @Override
    public Motor produce(char motor, String model, long power) {
        super.motor = motor;
        super.model = model;
        super.power = power;
        return  this;
    }
}
