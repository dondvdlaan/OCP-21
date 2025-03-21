package dev.manyroads.testground;

import java.util.Arrays;

public class Testje {

    private String naam;
    private int nummer;
    private int nogEenNummer;

    public Testje() {
    }

    public Testje(String naam, int nummer) {
        this.naam = naam;
        this.nummer = nummer;
    }

    public Testje( int nogEenNummer) {
        this("aap",100);
        this.nogEenNummer = nogEenNummer;
    }

    void arrayTest() {
        int[] ints = {1, 2, 3, 4, 5, 9, 8};
        Arrays.sort(ints);
        Arrays.stream(ints).sequential().forEach(System.out::print);
        System.out.println(Arrays.binarySearch(ints, 1));
    }

    void printArray(int... var) {
        Arrays.asList("ee");
        Arrays.stream(var).forEach(System.out::print);
    }

    void passByValue(Integer pass) {
        pass=11;
    }

    @Override
    public String toString() {
        return "Testje{" +
                "naam='" + naam + '\'' +
                ", nummer=" + nummer +
                ", nogEenNummer=" + nogEenNummer +
                '}';
    }

    public static void main(String[] args) {
        /*new Testje().printArray(1, 2, 3, 4, 5, 6, 7, 7, 7);
        Testje t = new Testje();
        Integer i = 10;
        System.out.println(i);
        new Testje().passByValue(i);
        System.out.println(i);

         */
        Testje t = new Testje(123);
        System.out.println(t.toString());


    }
}
