package dev.manyroads.referencing;

class Animal {
    protected String sound = "Animal Sound";
}

class Dog extends Animal {
    protected String sound = "Bark";
}

public class MainReferencing {
    public static void main(String[] args) {
        Animal animal = new Dog();
        System.out.println(((Dog) animal).sound);
    }
}

