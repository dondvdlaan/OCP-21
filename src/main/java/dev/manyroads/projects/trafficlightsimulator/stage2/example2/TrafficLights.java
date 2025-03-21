package dev.manyroads.projects.trafficlightsimulator.stage2.example2;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Traffic Lights model class.
 */
@Data
@AllArgsConstructor
public class TrafficLights {
    private int numberOfRoads;
    private int interval;

    public void addRoad() {
        numberOfRoads++;
    }

    public void deleteRoad() {
        numberOfRoads--;
    }
}
