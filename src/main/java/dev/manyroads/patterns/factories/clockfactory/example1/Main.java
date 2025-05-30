package dev.manyroads.patterns.factories.clockfactory.example1;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

class ClockFactory {

    private static final Map<String, Supplier<Clock>> CLOCK_MAP = Map.of(
            "SAND", SandClock::new,
            "DIGITAL", DigitalClock::new,
            "MECH", MechanicalClock::new
    );

    private final boolean produceToyClock;

    public ClockFactory(boolean produceToyClock) {
        this.produceToyClock = produceToyClock;
    }

    /**
     * It produces a clock according to a specified type: SAND, DIGITAL or MECH.
     * If some other type is passed, the method produces ToyClock.
     */
    public Clock produce(String type) {
        final Supplier<Clock> supplier = CLOCK_MAP.get(type);
        return supplier != null ? supplier.get() : produceToyClock ? new ToyClock() : null;
    }
}

/* Do not change code below */
interface Clock {

    void tick();
}

class SandClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...sand noise...");
    }
}

class DigitalClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...pim...");
    }
}

class MechanicalClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...clang mechanism...");
    }
}

class ToyClock implements Clock {

    @Override
    public void tick() {
        System.out.println("...tick...");
    }
}

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String type = scanner.next();
        final boolean produceToy = scanner.nextBoolean();
        final ClockFactory factory = new ClockFactory(produceToy);
        final Clock clock = factory.produce(type);
        if (clock == null) {
            System.out.println(clock);
        } else {
            clock.tick();
        }
    }
}
