package dev.manyroads.constructors;

public class Baby extends Papa {

    public Baby(int x) {
        System.out.println("G");
    }
    static{
        System.out.println("B");
    }
    {
        System.out.println("F");
    }
    public static void main(String[] args) {
        Baby b = new Baby(1);
        System.out.println("H");
        Baby bbb = new Baby(2);
    }
}
