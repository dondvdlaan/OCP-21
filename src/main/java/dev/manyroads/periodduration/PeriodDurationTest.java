package dev.manyroads.periodduration;

import java.time.Duration;
import java.time.Instant;

public class PeriodDurationTest {

    static final public void ttt(){

    }
    public static void main(String[] args) throws InterruptedException {
        var nu = Instant.now();
        Thread.sleep(5000);
        var later = Instant.now();
        System.out.println(Duration.between(nu,later).getSeconds());
    }
}
