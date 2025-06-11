package dev.manyroads.switchstatement;

import static dev.manyroads.switchstatement.TestSwitch.Day.WEDNESDAY;


public class TestSwitch {

    enum Dieren {
        KAT,
        HOND
    }

    static void switchje(Object o) {
//
//        switch (o) {
//            case Integer i when i > 0 -> System.out.println("groter dna 0: " + i);
//            case Integer i when i < 0 -> System.out.println("kleiner dna 0" + i);
//            case String s -> System.out.println("stingetje: " + s);
//            case Dieren.HOND -> System.out.println("Hondhe");
//            case null, default -> System.out.println("geen keuze");
//        }
    }

    enum Day {
        SUNDAY, MONDAY, TUESDAY,
        WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
    }

    static void switchjeDag(Day day) {
        int numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> {
                System.out.println(6);
                yield 6;
            }
            case TUESDAY -> {
                System.out.println(7);
                yield 7;
            }
            case THURSDAY, SATURDAY -> {
                System.out.println(8);
                yield 8;
            }
            case WEDNESDAY -> {
                System.out.println(9);
                yield 9;
            }
        };
    }

    public static void main(String[] args) {
        switchje(null);
        switchjeDag(WEDNESDAY);
    }
}
