package dev.manyroads.exceptions;

public class MyException extends Throwable {
    public  MyException(String message){
        super(message);
    }
}

class Main {
    public static void main(String[] args) {
        MyException myException = new MyException("This is a custom exception");
        System.out.println(myException.getMessage());
        Class<?> superClass = myException.getClass().getSuperclass();
        System.out.println(superClass.equals(Throwable.class));
    }
}
