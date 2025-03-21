package dev.manyroads.generics.typesafebox;

import java.util.Scanner;

/**
 * Create a type-safe Box class that has a single instance variable for the type it's defined with,
 * alongside appropriate constructor. Implement a method named 'get' that will return the value. Then,
 * you will be provided with a string, integer or a float. Your task is to use the Box class, put the
 * value provided into an object of Box and then return that value. Note that the box can hold any
 * data type. The input will be a single line of either string, integer or float and your output
 * should be the same value.
 *
 */
public class TypeSafeBox {
    public static void main(String[] args) {
        String value="";
        try(Scanner scanner = new Scanner(System.in)){
            if(scanner.hasNextInt()){
                Box<Integer> iBox = new Box<>(scanner.nextInt());
                System.out.println(iBox.get());
                return;
            }
            if (scanner.hasNextDouble()) {
                Box<Double> dBox = new Box<>(scanner.nextDouble());
                System.out.println(dBox.get());
                return;
            }
            if (scanner.hasNextLine()) {
                Box<String> sBox = new Box<>(scanner.nextLine());
                System.out.println(sBox.get());
                return;
            }
        }catch(IllegalStateException ex){
            System.out.println(ex.getMessage());;
        }
    }

}

class Box<T>{
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public void put(T value) {
        this.value = value;
    }
}
