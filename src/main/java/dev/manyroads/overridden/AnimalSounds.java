package dev.manyroads.overridden;

/**
 * Suppose you have two classes: "Animal" and "Dog". The class "Animal" has a method "speak()" that returns a String
 * "Animal sound...". The class "Dog" is a subclass of "Animal", but the method "speak()" in the "Dog" class should
 * return "Bark!". However, there's also a static method in the "Dog" class called "main()", which creates a Dog
 * object named "fido" and prints the result of calling "speak()" on "fido".
 */
public class AnimalSounds {
    public static void main(String[] args) {
        // Your code here
    }
}

class Animal {
    public String speak() {
        return "Animal sound...";
    }
}

class Dog extends Animal {
    public String speak() {
        return "Bark!";
    }

    public static void main(String[] args) {
        Dog fido = new Dog();
        System.out.println(fido.speak());
    }
}
