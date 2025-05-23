package dev.manyroads.comparable.cars;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        List<Car> cars = new ArrayList<>();
        Car car1 = new Car(876, "BMW", "white", 1400);
        Car car2 = new Car(345, "Mercedes", "black", 2000);
        Car car3 = new Car(470, "Volvo", "blue", 1800);

        cars.add(car1);
        cars.add(car2);
        cars.add(car3);

        //Collections.sort(cars);
        Collections.reverse(cars);
        cars.forEach(System.out::println);
        System.out.println();
        // ..or..
        //cars.stream().sorted(Car::compareTo).forEach(System.out::println);
        // ..or.. sort on model
        cars.stream().sorted(Car::compareTo2).forEach(System.out::println);
    }
}

class Car implements Comparable<Car> {

    @Getter
    private int number;
    @Getter
    private String model;
    private String color;
    private int weight;

    public Car(int number, String model, String color, int weight) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.weight = weight;
    }

    @Override
    public int compareTo(Car otherCar) {
        return getModel().compareTo(otherCar.getModel());
        //return Integer.compare(getNumber(), otherCar.getNumber());
        //return Integer.valueOf(getNumber()).compareTo(otherCar.getNumber());
    }

    public int compareTo2(Car otherCar) {
        return getModel().compareTo(otherCar.getModel());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return
                number == car.number &&
                        weight == car.weight &&
                        Objects.equals(model, car.model) &&
                        Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, color, weight);
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
