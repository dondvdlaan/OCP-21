package dev.manyroads.projects.trafficlightsimulator.stage2.example1;

public class Main {
    public static void main(String[] args) {
        var sc = new java.util.Scanner(System.in).useDelimiter("\n");
        System.out.println("Welcome to the traffic management system!");
        System.out.println("Input the number of roads:");
        int roads = sc.nextInt();
        System.out.println("Input the interval:");
        int interval = sc.nextInt();
        while (true) {
            System.out.println("Menu:\n1. Add road\n2. Delete road\n3. Open system\n0. Quit");
            switch (sc.nextInt()) {
                case 1 -> System.out.println("Road added");
                case 2 -> System.out.println("Road deleted");
                case 3 -> System.out.println("System opened");
                case 0 -> System.exit(0);
            }
        }
    }
}
