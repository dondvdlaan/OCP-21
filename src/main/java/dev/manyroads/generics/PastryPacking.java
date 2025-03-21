package dev.manyroads.generics;

/**
 * You were asked to help with Java programming for a pie company. At the moment, they bake pies, cakes, and tarts and pack them in
 * nice boxes to sell. Unfortunately, their approach to box programming is quite outdated and each pie type requires its own box class.
 * This approach is poorly scalable and will turn the situation into a nightmare with product range growth (imagine all these:
 * ApplePieBox, StrawberryPieBox, etc.).
 *
 * To avoid this, implement a universal Box class that will accommodate anything with put methods and give it back with get methods.
 */
public class PastryPacking {
    public static void main(String[] args) {
        Box<Cake> cakeBox = new Box<>();
        cakeBox.put(new Cake());
        System.out.println(cakeBox.get());
        Cake cake = cakeBox.get();
    }

}

class Box<T>{
    private T box;

    public T get() {
        return box;
    }

    void put(T type){
        this.box =type;
    }

}

class Cake { }

class Pie { }

class Tart { }