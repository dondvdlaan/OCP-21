package dev.manyroads.projects.trafficlightsimulator.stage1.example1;

import lombok.extern.slf4j.Slf4j;

/**
 * test-friendly (since capturable, mockable) wrapper class to the Logger for console printing.
 */
@Slf4j
public class ConsolePrinter {

    public final void printInfo(String message, Object... arguments) {
        System.out.println("logging: "+ log.isInfoEnabled());
        log.error("holita");
        log.info(message, arguments);
    }
}
