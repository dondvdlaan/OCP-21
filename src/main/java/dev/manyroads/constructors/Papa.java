package dev.manyroads.constructors;

public class Papa {
    static{
        System.out.println("A");
    }
    {
        System.out.println("C");
    }
    public Papa() {
        this(1);
        System.out.println("E");
    }

    public Papa(int nr) {
        System.out.println("D");
    }
}
