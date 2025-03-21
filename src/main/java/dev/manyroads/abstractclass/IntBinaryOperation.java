package dev.manyroads.abstractclass;

/**
 * You need to declare and implement two classes representing concrete operations: Addition and Multiplication.
 * The classes must extend the abstract class and implement the method perform().
 * The implementations should work in accordance with the class name.
 * Do not forget to write two-argument constructors in your classes.
 * <p>
 * Do NOT make your classes public.
 */
abstract class IntBinaryOperation {

    protected int firstArg;
    protected int secondArg;

    public IntBinaryOperation(int firstArg, int secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

    public abstract int perform();
}

class Addition extends IntBinaryOperation {

    Addition(int firstArg, int secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    public int perform() {
        return firstArg + secondArg;
    }
}

class Multiplication extends IntBinaryOperation {

    Multiplication(int firstArg, int secondArg) {
        super(firstArg, secondArg);
    }

    @Override
    public int perform() {
        return firstArg * secondArg;
    }
}

class TestIntBinaryOperation{
    public static void main(String[] args) {
        System.out.println( new Addition(1,2).perform());
        System.out.println( new Multiplication(1,2).perform());
    }
}


